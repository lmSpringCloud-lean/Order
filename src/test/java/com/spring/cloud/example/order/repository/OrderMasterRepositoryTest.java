package com.spring.cloud.example.order.repository;

import com.spring.cloud.example.order.OrderApplicationTests;
import com.spring.cloud.example.order.constant.Constants;
import com.spring.cloud.example.order.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerAddress("贵州");
        orderMaster.setBuyerName("张三丰");
        orderMaster.setBuyerOpenid("zhangsanfen12345");
        orderMaster.setBuyerPhone("18765431234");
        orderMaster.setOrderAmount(BigDecimal.valueOf(100));
        orderMaster.setOrderStatus(Constants.OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(Constants.PayStatus.SUCCESS.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(null != result);
        log.info(result.toString());
    }
}