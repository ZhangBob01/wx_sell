package com.bob.wx_sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 商品（包含类目）
 * @Auther: toudaizhi
 * @Date: 2018-12-21 17:27
 * @Description:
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 1248489397903849491L;

    @JsonProperty("name") //此注解更换json中的key
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> foods;
}
