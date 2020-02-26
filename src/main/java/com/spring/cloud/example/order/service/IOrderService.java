package com.spring.cloud.example.order.service;

import com.spring.cloud.example.order.dto.OrderDTO;

public interface IOrderService {
    /**
     * 创建订单
     * 1.查询商品信息（调用商品服务）
     * 2.计算总价
     * 3.扣库存（调用商品服务）
     * 4.订单入库
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单（只能卖家操作）
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
