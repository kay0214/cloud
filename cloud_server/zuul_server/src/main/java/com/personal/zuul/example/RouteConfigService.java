/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunpeikai
 * @version RouteConfigService, v0.1 2020/11/16 15:47
 * @description
 */
@Service
public class RouteConfigService {

    public List<RouteConfigVO> getAllRouteConfig(){
        List<String> uri = Arrays.asList("index", "/user/mobileModify", "/server/getBestServerAction", "/user/bindEmail", "/user/login", "/user/getUserInfo");
        List<String> apiName = Arrays.asList("首页", "修改手机号", "获取最优服务器", "绑定邮箱", "登录", "获取用户信息");
        List<RouteConfigVO> result = new ArrayList<>();
        // 假数据,实际应该从DB获取路由配置
        for(int i=0; i<uri.size() ; i++){
            RouteConfigVO configVO = new RouteConfigVO();
            configVO.setId((i+1) + "");
            configVO.setPath(uri.get(i));
            configVO.setServiceId("TEST-SERVER");
            configVO.setUrl(null);
            configVO.setRetryable(false);
            configVO.setEnabled(true);
            configVO.setStripPrefix(1);
            configVO.setApiName(apiName.get(i));
            configVO.setSecureVisitFlag(i%2 == 0 ? 0 : 1);
            result.add(configVO);
        }
        return result;
    }
}
