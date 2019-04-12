package com.bob.wx_sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 09:35
 * @Description:
 */
@Entity
@DynamicUpdate
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单ID. */
    private String orderId;

    /** 商品ID. */
    private String productId;

    /** 商品名称. */
    private String productName;

    /** 商品价格. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;
}
