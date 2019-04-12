package com.bob.wx_sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-22 16:56
 * @Description:
 */
@Data
public class ProductForm {
    private String productId;
    /** 商品名称. */
    private String productName;
    /** 单价. */
    private BigDecimal productPrice;
    /** 库存. */
    private Integer productStock;
    /** 描述. */
    private String productDescripttion;
    /** 小图. */
    private String productIcon;
    /** 类目编号. */
    private Integer categoryType;

}
