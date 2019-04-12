package com.bob.wx_sell.dataobject;

import com.bob.wx_sell.enums.ProductStatusEnum;
import com.bob.wx_sell.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 13:02
 * @Description:
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
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
    /** 状态，0正常1下架. */
    private  Integer productStatus = ProductStatusEnum.DOWN.getCode();
    /** 创建时间. */
    private Date createTime;
    /** 更新时间. */
    private Date updateTime;

    /**
     * 获取商品状态枚举
     * @return
     */
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getEnumByCode(productStatus,ProductStatusEnum.class);
    }

}
