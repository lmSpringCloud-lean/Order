package com.spring.cloud.example.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 用于测试接收rabbitMQ消息的
 */
@Slf4j
@Component
public class MqReceiver {

    /**
     * 接收方
     * @param message
     */
    //  1. 此种方法需提前在rabbitMQ中创建好对应的topic，否则程序启动会报错
    //@RabbitListener(queues = "myQueue")

    //  2. 此种方法可以自动创建Queue
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))

    //  3. 自动创建, Exchange和Queue的绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            //消息分组的时候用
            key = "myKey",
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("MqReceiver: {}", message);
    }
}
