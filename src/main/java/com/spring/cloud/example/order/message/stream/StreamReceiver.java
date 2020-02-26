package com.spring.cloud.example.order.message.stream;

import com.spring.cloud.example.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

//    @StreamListener(StreamClient.OUTPUT)
//    public void process(Object message){
//        log.info("StreamReceiver: {}" + message);
//    }

    // 接收OrderDTO对象消息
//    @StreamListener(StreamClient.OUTPUT)
//    public void process(OrderDTO message){
//        log.info("StreamReceiver: {}" + message);
//    }
}
