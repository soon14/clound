/*
 * *************************************************************************
 *   Copyright (c) 2018-2025, dreamlu.net All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the dreamlu.net developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: chunmeng.lu (qq596392912@gmail.com)
 * *************************************************************************
 */

package com.cloud.common.security.feign;

import com.netflix.hystrix.HystrixCommand;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixDelegatingContract;
import feign.hystrix.SetterFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * 自定义 Hystrix Feign 实现默认 fallBack
 *
 * @author L.cm
 */
public class MicroHystrixFeign {

	public static MicroHystrixFeign.Builder builder(FeignContext feignContext) {
		return new MicroHystrixFeign.Builder(feignContext);
	}

	public static final class Builder extends Feign.Builder {
		private Contract contract = new Contract.Default();
		private SetterFactory setterFactory = new SetterFactory.Default();
		private final FeignContext feignContext;

		public Builder(FeignContext feignContext) {
			this.feignContext = feignContext;
		}

		/**
		 * Allows you to override hystrix properties such as thread pools and command keys.
		 */
		public MicroHystrixFeign.Builder setterFactory(SetterFactory setterFactory) {
			this.setterFactory = setterFactory;
			return this;
		}

		@Override
		public <T> T target(Target<T> target) {
			Class<T> targetType = target.type();
			FeignClient feignClient = AnnotatedElementUtils.getMergedAnnotation(targetType, FeignClient.class);
			String factoryName = feignClient.name();
			SetterFactory setterFactoryBean = this.getOptional(factoryName, feignContext, SetterFactory.class);
			if (setterFactoryBean != null) {
				this.setterFactory(setterFactoryBean);
			}
			Class<?> fallback = feignClient.fallback();
			if (fallback != void.class) {
				return targetWithFallback(factoryName, feignContext, target, this, fallback);
			}
			Class<?> fallbackFactory = feignClient.fallbackFactory();
			if (fallbackFactory != void.class) {
				return targetWithFallbackFactory(factoryName, feignContext, target, this, fallbackFactory);
			}
			return build().newInstance(target);
		}

		@SuppressWarnings("unchecked")
		private <T> T targetWithFallbackFactory(String feignClientName, FeignContext context,
												Target<T> target,
												MicroHystrixFeign.Builder builder,
												Class<?> fallbackFactoryClass) {
			FallbackFactory<? extends T> fallbackFactory = (FallbackFactory<? extends T>)
					getFromContext("fallbackFactory", feignClientName, context, fallbackFactoryClass, FallbackFactory.class);
		/* We take a sample fallback from the fallback factory to check if it returns a fallback
		that is compatible with the annotated feign interface. */
			Object exampleFallback = fallbackFactory.create(new RuntimeException());
			Assert.notNull(exampleFallback,
					String.format(
							"Incompatible fallbackFactory instance for feign client %s. Factory may not produce null!",
							feignClientName));
			if (!target.type().isAssignableFrom(exampleFallback.getClass())) {
				throw new IllegalStateException(
						String.format(
								"Incompatible fallbackFactory instance for feign client %s. Factory produces instances of '%s', but should produce instances of '%s'",
								feignClientName, exampleFallback.getClass(), target.type()));
			}
			return builder.target(target, fallbackFactory);
		}


		private <T> T targetWithFallback(String feignClientName, FeignContext context,
                                         Target<T> target,
                                         MicroHystrixFeign.Builder builder, Class<?> fallback) {
			T fallbackInstance = getFromContext("fallback", feignClientName, context, fallback, target.type());
			return builder.target(target, fallbackInstance);
		}

		@SuppressWarnings("unchecked")
		private <T> T getFromContext(String fallbackMechanism, String feignClientName, FeignContext context,
									 Class<?> beanType, Class<T> targetType) {
			Object fallbackInstance = context.getInstance(feignClientName, beanType);
			if (fallbackInstance == null) {
				throw new IllegalStateException(String.format(
						"No %s instance of type %s found for feign client %s",
						fallbackMechanism, beanType, feignClientName));
			}

			if (!targetType.isAssignableFrom(beanType)) {
				throw new IllegalStateException(
						String.format(
								"Incompatible %s instance. Fallback/fallbackFactory of type %s is not assignable to %s for feign client %s",
								fallbackMechanism, beanType, targetType, feignClientName));
			}
			return (T) fallbackInstance;
		}

		@Nullable
		private <T> T getOptional(String feignClientName, FeignContext context, Class<T> beanType) {
			return context.getInstance(feignClientName, beanType);
		}

		/**
		 * @see #target(Class, String, Object)
		 */
		public <T> T target(Target<T> target, @Nullable T fallback) {
			return build(fallback != null ? new FallbackFactory.Default<T>(fallback) : null)
					.newInstance(target);
		}

		/**
		 * @see #target(Class, String, FallbackFactory)
		 */
		public <T> T target(Target<T> target, FallbackFactory<? extends T> fallbackFactory) {
			return build(fallbackFactory).newInstance(target);
		}

		/**
		 * Like {@link Feign#newInstance(Target)}, except with {@link HystrixCommand
		 * fallback} support.
		 *
		 * <p>Fallbacks are known values, which you return when there's an error invoking an http
		 * method. For example, you can return a cached result as opposed to raising an error to the
		 * caller. To use this feature, pass a safe implementation of your target interface as the last
		 * parameter.
		 * <p>
		 * Here's an example:
		 * <pre>
		 * {@code
		 *
		 * // When dealing with fallbacks, it is less tedious to keep interfaces small.
		 * interface GitHub {
		 *   @RequestLine("GET /repos/{owner}/{repo}/contributors")
		 *   List<String> contributors(@Param("owner") String owner, @Param("repo") String repo);
		 * }
		 *
		 * // This instance will be invoked if there are errors of any kind.
		 * GitHub fallback = (owner, repo) -> {
		 *   if (owner.equals("Netflix") && repo.equals("feign")) {
		 *     return Arrays.asList("stuarthendren"); // inspired this approach!
		 *   } else {
		 *     return Collections.emptyList();
		 *   }
		 * };
		 *
		 * GitHub github = HystrixFeign.builder()
		 *                             ...
		 *                             .target(GitHub.class, "https://api.github.com", fallback);
		 * }</pre>
		 *
		 * @see #target(Target, Object)
		 */
		public <T> T target(Class<T> apiType, String url, T fallback) {
			return target(new Target.HardCodedTarget<T>(apiType, url), fallback);
		}

		/**
		 * Same as {@link #target(Class, String, T)}, except you can inspect a source exception before
		 * creating a fallback object.
		 */
		public <T> T target(Class<T> apiType, String url, FallbackFactory<? extends T> fallbackFactory) {
			return target(new Target.HardCodedTarget<T>(apiType, url), fallbackFactory);
		}

		@Override
		public Feign.Builder invocationHandlerFactory(InvocationHandlerFactory invocationHandlerFactory) {
			throw new UnsupportedOperationException();
		}

		@Override
		public MicroHystrixFeign.Builder contract(Contract contract) {
			this.contract = contract;
			return this;
		}

		@Override
		public Feign build() {
			return build(null);
		}

		/**
		 * Configures components needed for hystrix integration.
		 */
		Feign build(@Nullable final FallbackFactory<?> nullableFallbackFactory) {
			super.invocationHandlerFactory((target, dispatch) ->
					new MicroHystrixInvocationHandler(target, dispatch, setterFactory, nullableFallbackFactory));
			super.contract(new HystrixDelegatingContract(contract));
			return super.build();
		}

		@Override
		public MicroHystrixFeign.Builder logLevel(Logger.Level logLevel) {
			return (MicroHystrixFeign.Builder) super.logLevel(logLevel);
		}

		@Override
		public MicroHystrixFeign.Builder client(Client client) {
			return (MicroHystrixFeign.Builder) super.client(client);
		}

		@Override
		public MicroHystrixFeign.Builder retryer(Retryer retryer) {
			return (MicroHystrixFeign.Builder) super.retryer(retryer);
		}

		@Override
		public MicroHystrixFeign.Builder logger(Logger logger) {
			return (MicroHystrixFeign.Builder) super.logger(logger);
		}

		@Override
		public MicroHystrixFeign.Builder encoder(Encoder encoder) {
			return (MicroHystrixFeign.Builder) super.encoder(encoder);
		}

		@Override
		public MicroHystrixFeign.Builder decoder(Decoder decoder) {
			return (MicroHystrixFeign.Builder) super.decoder(decoder);
		}

		@Override
		public MicroHystrixFeign.Builder mapAndDecode(ResponseMapper mapper, Decoder decoder) {
			return (MicroHystrixFeign.Builder) super.mapAndDecode(mapper, decoder);
		}

		@Override
		public MicroHystrixFeign.Builder decode404() {
			return (MicroHystrixFeign.Builder) super.decode404();
		}

		@Override
		public MicroHystrixFeign.Builder errorDecoder(ErrorDecoder errorDecoder) {
			return (MicroHystrixFeign.Builder) super.errorDecoder(errorDecoder);
		}

		@Override
		public MicroHystrixFeign.Builder options(Request.Options options) {
			return (MicroHystrixFeign.Builder) super.options(options);
		}

		@Override
		public MicroHystrixFeign.Builder requestInterceptor(RequestInterceptor requestInterceptor) {
			return (MicroHystrixFeign.Builder) super.requestInterceptor(requestInterceptor);
		}

		@Override
		public MicroHystrixFeign.Builder requestInterceptors(Iterable<RequestInterceptor> requestInterceptors) {
			return (MicroHystrixFeign.Builder) super.requestInterceptors(requestInterceptors);
		}
	}

}
