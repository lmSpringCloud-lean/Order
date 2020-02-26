package com.spring.cloud.example.order.exception;

import com.spring.cloud.example.order.constant.Constants;

public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public OrderException(Constants.Result result) {
        super(result.getMsg());
        this.code = result.getCode();
    }
}
