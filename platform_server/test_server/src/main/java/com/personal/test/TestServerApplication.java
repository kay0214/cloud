/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunpeikai
 * @version TestServerApplication, v0.1 2020/11/16 16:28
 * @description
 */
@RefreshScope
@RestController
@SpringBootApplication
public class TestServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestServerApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(TestServerApplication.class);

    @Value("${server.port}")
    String port;
    @Value("${test}")
    private String test;

    @GetMapping("/index")
    public String index(){
        String result = "this is test page - index - from port : " + port + " test : " + test;
        log.info(result);
        return result;
    }
}
