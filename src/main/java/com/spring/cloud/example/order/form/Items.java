package com.spring.cloud.example.order.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Items {

    @ApiModelProperty(value = "产品ID", example = "1423113435324", dataType = "String")
    @JsonProperty("productId")
    @NotEmpty(message = "产品ID不能为空")
    private String productId;

    @ApiModelProperty(value = "购买数量", example = "2")
    @JsonProperty("productQuantity")
    @NotNull(message = "购买数量不能为空")
    private Integer productQuantity;
}
