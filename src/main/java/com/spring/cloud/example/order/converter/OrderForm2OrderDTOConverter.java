package com.spring.cloud.example.order.converter;

import com.spring.cloud.example.order.constant.Constants;
import com.spring.cloud.example.order.dataobject.OrderDetail;
import com.spring.cloud.example.order.dto.OrderDTO;
import com.spring.cloud.example.order.exception.OrderException;
import com.spring.cloud.example.order.form.Items;
import com.spring.cloud.example.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderForm,orderDTO);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        if (CollectionUtils.isEmpty(orderForm.getItems())) {
            log.error("[创建订单] 购物车信息为空！");
            throw new OrderException(Constants.Result.CART_EMPTY);
        }
        for (Items items : orderForm.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(items,orderDetail);
            orderDetailList.add(orderDetail);
        }
        orderDTO.setOrderDetails(orderDetailList);
        return orderDTO;
    }
}
