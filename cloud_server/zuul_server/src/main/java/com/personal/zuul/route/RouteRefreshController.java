/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunpeikai
 * @version RouteRefreshController, v0.1 2020/11/16 09:28
 * @description 手动刷新路由配置,即时刷新
 */
@RestController
public class RouteRefreshController {

    @Autowired
    private RouteRefreshService service;

    @GetMapping("/refreshRoute")
    public String refreshRoute(){
        service.refreshRoute();
        return "route refresh complete ... ";
    }
}
