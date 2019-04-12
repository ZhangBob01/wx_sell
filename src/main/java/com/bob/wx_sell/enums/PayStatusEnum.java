package com.bob.wx_sell.enums;

import lombok.Getter;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 17:26
 * @Description:
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
