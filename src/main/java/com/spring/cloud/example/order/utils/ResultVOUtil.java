package com.spring.cloud.example.order.utils;

import com.spring.cloud.example.order.VO.ResultVO;
import com.spring.cloud.example.order.constant.Constants;
import org.springframework.util.StringUtils;

public class ResultVOUtil {
    public static ResultVO success(Object data) {
        ResultVO result = new ResultVO();
        result.setCode(Constants.Result.SUCCESS.getCode());
        result.setMsg(Constants.Result.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static ResultVO success() {
        ResultVO result = new ResultVO();
        result.setCode(Constants.Result.SUCCESS.getCode());
        result.setMsg(Constants.Result.SUCCESS.getMsg());
        return result;
    }

    public static ResultVO error(Integer code, String Msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        if (!StringUtils.isEmpty(Msg)) {
            result.setMsg(Msg);
        } else {
            result.setMsg("失败");
        }
        return result;
    }
}
