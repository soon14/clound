package com.cloud.cas.config;

import com.cloud.cas.filter.LoginFilter;
import com.dragonsoft.dcuc.client.authentication.AuthenticationFilter;
import com.dragonsoft.dcuc.client.session.SingleSignOutFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/8/22 9:41
 * @description:
 * @modified By:wengshij
 */
@Configuration
@AllArgsConstructor
public class CasConfig {


    @Bean
    public FilterRegistrationBean loginFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter");
        LoginFilter loginFilter=new LoginFilter();
        registrationBean.setFilter(loginFilter);
        registrationBean.setOrder(3);
        List<String> urlList = new ArrayList<String>();
        urlList.add("/*");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean authenticationFilterBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("authenticationFilter");
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        registrationBean.setFilter(authenticationFilter);
        registrationBean.setOrder(2);
        List<String> urlList = new ArrayList<String>();
        urlList.add("/*");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean CASSignOutFilterRegistration() {
        FilterRegistrationBean<SingleSignOutFilter> registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        registration.addUrlPatterns(new String[]{"/*"});
        registration.setName("CASSignOutFilter");
        registration.setOrder(1);
        return registration;
    }
}
