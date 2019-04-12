package com.bob.wx_sell.utils;

import java.util.Random;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-25 16:02
 * @Description:
 */
public class StringUtil {

    /**
     * 生成主键
     * 格式：时间加随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();

        Integer num = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(num);
    }
}
