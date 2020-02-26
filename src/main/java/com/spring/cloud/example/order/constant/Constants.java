package com.spring.cloud.example.order.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Constants {
    /**
     * 订单状态
     */
    @Getter
    @AllArgsConstructor
    enum OrderStatus {
        NEW(0,"新订单"),
        FINISHED(1,"完结"),
        CANCEL(2,"取消")
        ;
        private final Integer code;
        private final String msg;
    }
    /**
     * 支付状态
     */
    @Getter
    @AllArgsConstructor
    enum PayStatus {
        WAIT(0,"等待支付"),
        SUCCESS(1,"支付成功"),
        ;
        private final Integer code;
        private final String msg;
    }
    /**
     * 支付状态
     */
    @Getter
    @AllArgsConstructor
    enum Result {
        SUCCESS(0,"成功"),
        PARAM_ERROR(1,"参数错误"),
        CART_EMPTY(2,"购物车为空"),
        ORDER_NOT_EXIST(3,"订单不存在"),
        ORDER_STATUS_ERROR(4,"订单状态错误"),
        ORDER_detail_NOT_EXIST(5,"订单详情不存在"),
        ;
        private final Integer code;
        private final String msg;
    }
}
