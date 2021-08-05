package com.zp.stream;

import com.zp.stream.channel.TestChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({TestChannel.class})
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean("stream-test-topic.errors")
    MessageChannel testoutPutErrorChannel() {
        return new PublishSubscribeChannel();
    }


}
