package com.spring.cloud.example.order.message;

import com.spring.cloud.example.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * 发送rabbitMQ消息测试
 */
@Component
public class MqSendTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        // QueueName + message 发送消息
//        amqpTemplate.convertAndSend("myQueue", "now " + new Date());
        //使用 exchange + routingKey + message
        amqpTemplate.convertAndSend("myExchange","myKey", "now " + new Date());

    }
}