package com.zp.stream.controller;

import com.zp.stream.channel.TestChannel;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
public class SendMessageController {
    @Resource
    private TestChannel testChannel;

    @ResponseBody
    @RequestMapping(value = "send", method = RequestMethod.GET)
    public String sendMessage() {
        String messageId = UUID.randomUUID().toString();
        Message<String> message = MessageBuilder
                .withPayload("this is a test:" + messageId)
                .setHeader(MessageConst.PROPERTY_TAGS, "test")
                .build();
        try {
            testChannel.output().send(message);
            return messageId + "发送成功";
        } catch (Exception e) {
            return messageId + "发送失败，原因：" + e.getMessage();
        }
    }
}
