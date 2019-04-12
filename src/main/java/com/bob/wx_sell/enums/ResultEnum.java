package com.bob.wx_sell.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-25 14:13
 * @Description:
 */

@Getter
public enum ResultEnum {

    param_error(1, "参数错误"),
    product_not_exist(10, "商品不存在"),
    product_stock_error(11,"商品库存不足"),
    order_not_exist(12,"订单不存在"),
    orderdetail_not_exist(13, "订单详情不存在"),
    order_status_error(14,"订单状态错误" ),
    order_update_error(15, "订单更新失败"),
    order_detail_empty(16, "订单详情为空"),
    pay_status_error(17, "支付状态错误"),
    cart_empty(18, "购物车不能为空"),
    openid_empty(19, "openid为空"),
    order_id_empty(20, "orderId为空"),
    openid_owner_error(21, "订单openid不一致"),
    wechat_mp_error(22, "微信公众号方面错误"),
    wxpay_notify_money_verify_error(23, "微信支付以不同通知金额校验不通过"),
    login_fail(24, "登录失败，用户信息错误"),
    logout_success(25, "登出成功");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultEnum() {
    }
}
