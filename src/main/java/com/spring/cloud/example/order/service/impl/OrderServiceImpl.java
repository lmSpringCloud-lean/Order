package com.spring.cloud.example.order.service.impl;

import com.spring.cloud.example.order.constant.Constants;
import com.spring.cloud.example.order.dataobject.OrderDetail;
import com.spring.cloud.example.order.dataobject.OrderMaster;
import com.spring.cloud.example.order.dto.OrderDTO;
import com.spring.cloud.example.order.exception.OrderException;
import com.spring.cloud.example.order.repository.OrderDetailRepository;
import com.spring.cloud.example.order.repository.OrderMasterRepository;
import com.spring.cloud.example.order.service.IOrderService;
import com.spring.cloud.example.order.utils.KeyUtil;
import com.spring.cloud.example.product.client.ProductClient;
import com.spring.cloud.example.product.common.DecreaseStockInput;
import com.spring.cloud.example.product.common.ProductInfoOutput;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private OrderMasterRepository masterRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        //秒杀业务设计思想
        //商品服务中的库存量在redis中保存
        //开启redis分布式锁
        //1.读redis
        //2.判断redis中库存是否充足，减库存并将新值回写redis
        //关闭redis分布式锁
        //订单入库异常，需要手动回滚redis

        String orderId = KeyUtil.genUniqueKey();

        //  查询商品信息
        List<String> productIdList = orderDTO.getOrderDetails().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.productInfoList(productIdList);

        //  计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            for (ProductInfoOutput productInfoOutput : productInfoList) {
                if (StringUtils.equals(orderDetail.getProductId(), productInfoOutput.getProductId())) {
                    orderAmount = productInfoOutput.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfoOutput, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //  订单详情入库
                    detailRepository.save(orderDetail);
                }
            }
        }

        //  扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetails().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        //  订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderStatus(Constants.OrderStatus.NEW.getCode());
        orderDTO.setPayStatus(Constants.PayStatus.WAIT.getCode());
        orderDTO.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);

        masterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(String orderId) {
        // 1.查询订单
        Optional<OrderMaster> orderMasterOptional = masterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(Constants.Result.ORDER_NOT_EXIST);
        }
        // 2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!Constants.OrderStatus.NEW.getCode().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(Constants.Result.ORDER_STATUS_ERROR);
        }
        // 3.修改订单状态为完结
        orderMaster.setOrderStatus(Constants.OrderStatus.FINISHED.getCode());
        OrderMaster orderMasterResult = masterRepository.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList = detailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(Constants.Result.ORDER_detail_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterResult, orderDTO);
        orderDTO.setOrderDetails(orderDetailList);

        return orderDTO;
    }
}
