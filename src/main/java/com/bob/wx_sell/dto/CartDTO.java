package com.bob.wx_sell.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-25 16:30
 * @Description:
 */
@Data
public class CartDTO {

    /** 商品Id. */
    private String productId;

    /** 商品数量. */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
