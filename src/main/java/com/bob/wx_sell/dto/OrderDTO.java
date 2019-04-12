package com.bob.wx_sell.dto;

import com.bob.wx_sell.dataobject.OrderDetail;
import com.bob.wx_sell.enums.OrderStatusEnum;
import com.bob.wx_sell.enums.PayStatusEnum;
import com.bob.wx_sell.utils.EnumUtil;
import com.bob.wx_sell.utils.serialize.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-25 09:53
 * @Description:
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
//此注解去除对象中为null的数据
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String OrderId;

    /** 买家姓名. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信openid. */
    private String buyerOpenid;

    /** 订单金额. */
    private BigDecimal orderAmount;

    /** 订单状态. */
    private Integer orderStatus;

    /** 支付状态. */
    private Integer payStatus;

    /** 创建时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    /**
     * 获取订单状态枚举
     * @return
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getEnumByCode(orderStatus,OrderStatusEnum.class);
    }

    /**
     * 获取支付状态枚举
     * @return
     */
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getEnumByCode(payStatus,PayStatusEnum.class);
    }
}
