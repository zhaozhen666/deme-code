package com.zp.stream;

import com.zp.stream.channal.TestChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.ValidationException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({TestChannel.class})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }

    @StreamListener("input")
    public void receiveInput(@Payload Message message) throws ValidationException {
        //System.out.println("input1 receive: " + message.getPayload() + ", foo header: " + message.getHeaders().get("foo"));
        throw new RuntimeException("oops");
    }
    @ServiceActivator(inputChannel = "stream-test-topic.test.errors")
    public void receiveConsumeError(Message message){
        System.out.println("receive error msg"+message.getPayload());
    }
}
