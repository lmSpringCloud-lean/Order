package com.spring.cloud.example.order.message.stream;

import com.spring.cloud.example.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StreamReceiverTest extends OrderApplicationTests {

    @Autowired
    private StreamClient streamClient;

    @Test
    public void process() {
        String message = "now " + new Date();
        streamClient.output().send(MessageBuilder
                .withPayload(message)
                .build());
    }
}