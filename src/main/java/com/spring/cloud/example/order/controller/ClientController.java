package com.spring.cloud.example.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/client")
@RefreshScope
public class ClientController {

    //哪里用配置哪里的类上面写@RefreshScope
    @Value("${env}")
    private String env;

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private ProductClient1 productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //  第一种硬编码调用方式(直接使用restTemplate)
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject("http://127.0.0.1:8080/server/msg",String.class);

        //  第二种方式（使用LoadBalancerClient通过应用名获取url，再用restTemplate）
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/server/msg";
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(url, String.class);

        //  第三种方式 （使用@LoadBalanced,可在RestTemplate里使用应用名字）
//        String result = restTemplate.getForObject("http://PRODUCT/server/msg",String.class);

        //  使用Feign方式调用（声明式REST客户端【伪RPC】，接口+注解）
//        String result = productClient.productMsg();
//        log.info("result={}" + result);

        String result = "1";
        return result;
    }

//    @GetMapping("/getProductList")
//    public String getProductList(){
//        List<ProductInfo> productInfoList = productClient.productInfoList(Arrays.asList("164103465734242707"));
//        for (ProductInfo productInfo : productInfoList) {
//            log.info("productInfo={}", productInfo.toString());
//        }
//        return "ok";
//    }
//
//    @GetMapping("/decreaseStock")
//    public String decreaseStock(){
//        productClient.decreaseStock(Arrays.asList(new CartDTO("164103465734242707", 3)));
//        return "ok";
//    }

    @GetMapping("/print")
    public String printEnv(){
        return env;
    }
}
