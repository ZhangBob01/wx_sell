package com.bob.wx_sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * http请求返回最外层信息
 * @Auther: toudaizhi
 * @Date: 2018-12-21 16:59
 * @Description:
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1248489397903849498L;

    /** 返回码. */
    private Integer code;

    /** 返回信息. */
    private String msg;

    /** 返回数据. */
    private T data;
}
