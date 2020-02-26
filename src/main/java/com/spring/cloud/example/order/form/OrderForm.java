package com.spring.cloud.example.order.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderForm {
    @ApiModelProperty(value = "下单用户姓名",example = "张三")
    @JsonProperty("name")
    @NotBlank(message = "姓名必填")
    private String buyerName;

    @ApiModelProperty(value = "手机号", example = "18868822111")
    @JsonProperty("phone")
    @NotBlank(message = "手机号必填")
    private String buyerPhone;

    @ApiModelProperty(value = "地址", example = "贵阳高新区")
    @JsonProperty("address")
    @NotBlank(message = "地址必填")
    private String buyerAddress;

    @ApiModelProperty(value = "用户的微信openid", example = "ew3euwhd7sjw9diwkq")
    @JsonProperty("openid")
    @NotBlank(message = "openid必填")
    private String buyerOpenid;

    @ApiModelProperty(value = "购物车列表")
    @NotEmpty(message = "购物车不能为空")
    @Valid
    private List<Items> items;
}
