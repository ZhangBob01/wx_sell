package com.bob.wx_sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 多字段验证：
 * 1.新建多字段实体类
 * 2.加validation中NotEmpty注解
 * 3.在api方法中public ResultVO<Map<String,String>> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult){}
 * 4.参数验证  if(bindingResult.hasErrors()){}
 * 5.获取错误信息，bindingResult.getFieldError().getDefaultMessage()
 * @Auther: toudaizhi
 * @Date: 2019-01-02 14:26
 * @Description:
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址不能为空")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid不能为空")
    private String openid;

    /**
     * 订单详情
     */
    @NotEmpty(message = "详情不能为空")
    private String items;

}
