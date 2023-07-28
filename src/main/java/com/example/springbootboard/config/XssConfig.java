package com.example.springbootboard.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class XssConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> getFilterRegistrationBean() {
        FilterRegistrationBean<XssEscapeServletFilter> xssRegistrationBean = new FilterRegistrationBean<>();
        xssRegistrationBean.setFilter(new XssEscapeServletFilter());
        xssRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        xssRegistrationBean.addUrlPatterns("/*");
        return xssRegistrationBean;
    }
}
