package com.zp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication9006 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication9006.class, args);
    }
}
