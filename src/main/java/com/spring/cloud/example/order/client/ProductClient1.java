package com.spring.cloud.example.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product")
public interface ProductClient1 {

    @GetMapping("/server/msg")
    String productMsg();

//    @PostMapping("/product/listForOrder")
//    List<ProductInfo> productInfoList(@RequestBody List<String> productIdList);

//    @PostMapping("/product/decreaseStock")
//    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
