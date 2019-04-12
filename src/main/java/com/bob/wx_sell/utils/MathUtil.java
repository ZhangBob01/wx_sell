package com.bob.wx_sell.utils;

import com.sun.tools.corba.se.idl.constExpr.Equal;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-08 18:28
 * @Description:
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    public static Boolean equals(Double d1, Double d2){

        Double result = Math.abs(d1-d2);
        if(result < MONEY_RANGE){
            return true;
        }
        return false;
    }
}
