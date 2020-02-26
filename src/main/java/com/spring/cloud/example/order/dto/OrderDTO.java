package com.spring.cloud.example.order.dto;

import com.spring.cloud.example.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    //  订单ID
    private String orderId;
    //  买家名字
    private String buyerName;
    //  买家电话
    private String buyerPhone;
    //  买家地址
    private String buyerAddress;
    //  买家微信openid
    private String buyerOpenid;
    //  订单总金额
    private BigDecimal orderAmount;
    //  订单状态, 默认为0新下单
    private Integer orderStatus;
    //  支付状态, 默认0未支付
    private Integer payStatus;

    private List<OrderDetail> orderDetails;
}
