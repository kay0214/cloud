/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.config;

import com.personal.zuul.example.RouteConfigService;
import com.personal.zuul.filter.AccessFilter;
import com.personal.zuul.filter.ReturnFilter;
import com.personal.zuul.filter.XSSFilter;
import com.personal.zuul.route.CustomRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author sunpeikai
 * @version ZuulConfig, v0.1 2020/11/16 14:14
 * @description
 */
@Configuration
public class ZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private DispatcherServletPath dispatcherServletPath;

    @Autowired
    private RouteConfigService routeConfigService;

    /**
     * 路由加载
     * */
    @Bean
    public CustomRouteLocator routeLocator() {
        return new CustomRouteLocator(dispatcherServletPath.getPrefix(), this.zuulProperties, this.routeConfigService);
    }

    /**
     * 请求前置处理过滤器 - 请求参数处理
     * */
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    /**
     * 请求后置处理过滤器 - 响应参数处理
     * */
    @Bean
    public ReturnFilter returnFilter() {
        return new ReturnFilter();
    }

    /**
     * 防止XSS攻击过滤器
     * */
    @Bean
    public XSSFilter xssFilter() {
        return new XSSFilter();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 跨域处理
     * */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        //这个请求头在https中会出现,但是有点问题，下面我会说
        //config.addExposedHeader("X-forwared-port, X-forwarded-host");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
