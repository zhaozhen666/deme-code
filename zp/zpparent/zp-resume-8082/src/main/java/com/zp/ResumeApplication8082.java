package com.zp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan("com.zp.pojo")
//@EnableEurekaClient
@EnableDiscoveryClient
public class ResumeApplication8082 {

    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication8082.class,args);
    }

}
