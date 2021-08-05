package com.zp.stream.channel;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ErrorProducerService {

    @ServiceActivator(inputChannel = "stream-test-topic.errors")
    public void receiveProducerError(Message message) {
        System.out.println("receive error msg :" + message);
    }
}
