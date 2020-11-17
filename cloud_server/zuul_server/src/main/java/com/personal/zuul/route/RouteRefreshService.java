/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author sunpeikai
 * @version RouteRefreshService, v0.1 2020/11/16 09:24
 * @description 刷新路由配置
 */
@Service
public class RouteRefreshService {
    public final static Logger log = LoggerFactory.getLogger(RouteRefreshService.class);

    @Autowired
    ApplicationEventPublisher publisher;
    @Autowired
    RouteLocator routeLocator;

    public void refreshRoute() {
        log.info("refresh route ... ");
        // 刷新路由
        CustomRouteLocator.setDoRefresh(true);
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }
}
