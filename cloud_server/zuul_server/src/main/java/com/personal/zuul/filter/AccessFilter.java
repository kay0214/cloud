/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunpeikai
 * @version AccessFilter, v0.1 2020/11/16 14:16
 * @description
 */
public class AccessFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 前置处理过滤器
     * 针对业务做
     * */
    @Override
    public Object run() throws ZuulException {
        return null;
    }

    /**
     * 过滤器类型
     * */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序
     * */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    /**
     * true:走这个过滤器
     * false:不走这个过滤器
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
