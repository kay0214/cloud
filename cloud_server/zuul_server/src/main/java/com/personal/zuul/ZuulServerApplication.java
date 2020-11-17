/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author sunpeikai
 * @version ZuulServerApplication, v0.1 2020/11/13 16:06
 * @description zuul server for spring cloud
 */
@RestController
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.personal")
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test/{server}")
    public String test(@PathVariable String server){
        return restTemplate.getForObject("http://" + server + "/index", String.class);
    }
}