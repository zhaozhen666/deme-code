package com.zp.stream.channal;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestChannel {
    @Input("input")
    SubscribableChannel input();
}
