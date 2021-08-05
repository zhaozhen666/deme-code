package com.zhao.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass
public class StartBeanAutoConfiguration {
    static {
        System.out.println("custom auto configuration init ");
    }

    @Bean
    public StarterBean starterBean() {
        return new StarterBean();
    }
}
