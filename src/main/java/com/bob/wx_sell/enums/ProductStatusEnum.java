package com.bob.wx_sell.enums;

import lombok.Getter;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 15:18
 * @Description:
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {

    UP(1,"在架"),
    DOWN(0,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
