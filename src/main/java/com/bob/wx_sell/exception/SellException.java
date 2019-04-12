package com.bob.wx_sell.exception;

import com.bob.wx_sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-25 11:13
 * @Description:
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
