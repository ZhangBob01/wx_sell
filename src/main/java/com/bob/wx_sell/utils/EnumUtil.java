package com.bob.wx_sell.utils;

import com.bob.wx_sell.enums.CodeEnum;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-20 14:02
 * @Description:
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> enumClass ){
        for(T each:enumClass.getEnumConstants()){
            if(each.getCode().equals(code)){
                return each;
            }
        }
        return null;
    }
}
