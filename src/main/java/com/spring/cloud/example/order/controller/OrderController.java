package com.spring.cloud.example.order.controller;

import com.spring.cloud.example.order.VO.ResultVO;
import com.spring.cloud.example.order.constant.Constants;
import com.spring.cloud.example.order.converter.OrderForm2OrderDTOConverter;
import com.spring.cloud.example.order.dto.OrderDTO;
import com.spring.cloud.example.order.exception.OrderException;
import com.spring.cloud.example.order.form.OrderForm;
import com.spring.cloud.example.order.service.IOrderService;
import com.spring.cloud.example.order.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api("订单 API")
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid @RequestBody OrderForm orderForm,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm={}", orderForm);
            throw new OrderException(Constants.Result.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //  orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> data = new HashMap<>();
        data.put("orderId",result.getOrderId());
        return ResultVOUtil.success(data);
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @PostMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId){
        OrderDTO orderDTO = orderService.finish(orderId);
        return ResultVOUtil.success(orderDTO);
    }
}
