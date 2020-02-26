package com.spring.cloud.example.order.controller;

import com.spring.cloud.example.order.dto.OrderDTO;
import com.spring.cloud.example.order.message.stream.StreamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StreamReceiverController {

    @Autowired
    private StreamClient streamClient;

    /**
     * 发送OrderDTO方法
     */
    @GetMapping("/sendMessage")
    public void process(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
