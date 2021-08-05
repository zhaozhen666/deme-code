package com.zp.eurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplicaton8762 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplicaton8762.class, args);
    }
}
