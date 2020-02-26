package com.spring.cloud.example.order.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * http请求返回的最外层对象
 * @param <T>
 */
@Data
public class ResultVO<T> {
    @ApiModelProperty(value = "错误码", dataType = "Integer", name = "code", example = "0")
    @NotBlank(message = "错误码不可为空")
    private Integer code;
    @ApiModelProperty(value = "提示信息", dataType = "String", name = "msg", example = "成功")
    private String msg;
    //  具体内容
    @ApiModelProperty("具体内容")
    private T data;
}
