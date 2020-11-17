/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.example;

/**
 * @author sunpeikai
 * @version RouteConfigVO, v0.1 2020/11/16 15:48
 * @description
 */
public class RouteConfigVO {
    private String id;

    private String path;

    private String serviceId;

    private String url;

    private Boolean retryable;

    private Boolean enabled;

    private Integer stripPrefix;

    private String apiName;

    private Integer secureVisitFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(Integer stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Integer getSecureVisitFlag() {
        return secureVisitFlag;
    }

    public void setSecureVisitFlag(Integer secureVisitFlag) {
        this.secureVisitFlag = secureVisitFlag;
    }
}
