/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.route;

import com.personal.zuul.example.RouteConfigService;
import com.personal.zuul.example.RouteConfigVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunpeikai
 * @version CustomRouteLocator, v0.1 2020/11/13 17:56
 * @description 自定义路由获取
 */
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    public final static Logger log = LoggerFactory.getLogger(CustomRouteLocator.class);

    private final ZuulProperties properties;

    private final RouteConfigService routeConfigService;

    // 是否刷新路由 - 启动时自动刷新一次(设为false指启动时不刷新,因为Scheduled在app启动时会执行一次)
    private static boolean doRefresh = false;

    public CustomRouteLocator(String servletPath, ZuulProperties properties, RouteConfigService routeConfigService) {
        super(servletPath, properties);
        this.properties = properties;
        this.routeConfigService = routeConfigService;
        log.info("servletPath[{}], properties defined route prefix[{}]", servletPath, properties.getPrefix());
    }

    /**
     * 设置是否刷新路由
     * */
    public static synchronized void setDoRefresh(boolean doRefresh) {
        CustomRouteLocator.doRefresh = doRefresh;
    }

    @Override
    public void refresh() {
        // 阻断locator每30秒刷新一次
        if(doRefresh){
            // 阻止刷新
            setDoRefresh(false);
            doRefresh();
        }
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        // 从配置文件中加载路由信息
        routesMap.putAll(super.locateRoutes());
        // 自定义(可以从DB)加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        // 优化一下配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // 拼接路径'/'
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            // 拼接路由前缀,在配置文件配置的zuul.prefix
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        log.info("locateRoutes is : {}", values);
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        log.debug("load zuul routes from DB ... ");
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        // 从其他地方加载路由配置
        // 比如从mysql加载到路由,然后将路由配置缓存到redis
        List<RouteConfigVO> routeConfigs = routeConfigService.getAllRouteConfig();
        if(routeConfigs != null && routeConfigs.size() > 0){
            for (RouteConfigVO routeConfigVO : routeConfigs) {
                ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
                // 去掉请求前缀
                zuulRoute.setStripPrefix(false);
                try {
                    BeanUtils.copyProperties(routeConfigVO, zuulRoute);
                } catch (Exception e) {
                    log.error("load zuul routes from DB error", e);
                }
                routes.put(zuulRoute.getPath(), zuulRoute);
            }
        }
        return routes;
    }
}
