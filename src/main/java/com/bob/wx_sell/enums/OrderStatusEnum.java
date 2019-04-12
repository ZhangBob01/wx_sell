package com.bob.wx_sell.enums;

import lombok.Getter;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 17:20
 * @Description:
 */
@Getter
public enum  OrderStatusEnum implements CodeEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
