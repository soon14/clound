

package com.cloud.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.security.exception.MicroAuth2Exception;
import lombok.SneakyThrows;

/**
 * @author ygnet
 * @date 2018/11/16
 * <p>
 * OAuth2 异常格式化
 */
public class MicroAuth2ExceptionSerializer extends StdSerializer<MicroAuth2Exception> {
	public MicroAuth2ExceptionSerializer() {
		super(MicroAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(MicroAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
