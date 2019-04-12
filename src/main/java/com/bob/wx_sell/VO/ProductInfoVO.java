package com.bob.wx_sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 17:34
 * @Description:
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 1248489397903849499L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
