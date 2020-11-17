/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author sunpeikai
 * @version RouteRefreshScheduler, v0.1 2020/11/16 09:26
 * @description 定时刷新路由配置
 */
@Component
@EnableScheduling
public class RouteRefreshScheduler {
    public final static Logger log = LoggerFactory.getLogger(RouteRefreshScheduler.class);

    @Autowired
    private RouteRefreshService service;

    /**
     * 每5分钟刷新一次路由配置
     */
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void autoRefreshRoute() {
        log.debug("auto refresh route ... ");
        service.refreshRoute();
    }
}
