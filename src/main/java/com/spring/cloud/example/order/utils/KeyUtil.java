package com.spring.cloud.example.order.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 此处主键生成不一定唯一，若想唯一应单独起一个生成唯一主键的服务，供大家调用（此处简化该步骤）
     * 生成唯一的主键
     * 格式：时间戳+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
