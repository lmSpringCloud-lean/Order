package com.spring.cloud.example.order.message;

import com.spring.cloud.example.order.utils.GsonUtil;
import com.spring.cloud.example.product.common.ProductInfoOutput;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProductInfoReceiver {

    private static final String PRDUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        //message ==> ProductInfoOutput
//        ProductInfoOutput output = GsonUtil.gsonToBean(message, ProductInfoOutput.class);
        List<ProductInfoOutput> outputList = null;
        try {
//            outputList = GsonUtil.gsonToList(message, ProductInfoOutput.class);
            outputList = GsonUtil.myGsonToList(message, ProductInfoOutput[].class);
        } catch (Exception e) {
            ProductInfoOutput output = GsonUtil.gsonToBean(message, ProductInfoOutput.class);
            outputList = new ArrayList<>();
            outputList.add(output);
        }
        log.info("从队列[{}]接收到消息: {}", "productInfo", outputList);

        //存储到redis中
        for(ProductInfoOutput output : outputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRDUCT_STOCK_TEMPLATE, output.getProductId()),
                    String.valueOf(output.getProductStock()));
        }
    }
}
