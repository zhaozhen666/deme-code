package com.zp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        Integer forObject =
                restTemplate.getForObject("http://localhost:8081/resume/openstate/"
                        + userId, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
                userId + "的默认简历当前状态为：" + forObject);
        return forObject;
    }
    @GetMapping("/checkAndBegin/{userId}")
    public Integer findResumeOpenStateEureka(@PathVariable Long userId) {
//        List<ServiceInstance> list = discoveryClient.getInstances("lagou-service-resume");
//        ServiceInstance serviceInstance = list.get(0);
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
        String url = "http://zhao-service-resume/resume/openstate/"+userId;
        System.out.println("从eureka中获取了请求地址"+url);
        Integer forObject =
                restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
}
