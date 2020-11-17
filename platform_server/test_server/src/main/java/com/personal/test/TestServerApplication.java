/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunpeikai
 * @version TestServerApplication, v0.1 2020/11/16 16:28
 * @description
 */
@RestController
@SpringBootApplication
public class TestServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestServerApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @GetMapping("/index")
    public String index(){
        System.out.println("port:" + port);
        return "this is test page - index - from port : " + port;
    }
}
