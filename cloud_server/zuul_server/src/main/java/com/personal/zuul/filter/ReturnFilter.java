/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author sunpeikai
 * @version ReturnFilter, v0.1 2020/11/16 14:17
 * @description
 */
public class ReturnFilter extends ZuulFilter {

    /**
     * 后置处理过滤器
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
        return FilterConstants.POST_TYPE;
    }

    /**
     * 过滤器顺序
     * */
    @Override
    public int filterOrder() {
        return 0;
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
