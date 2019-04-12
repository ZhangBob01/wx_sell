package com.bob.wx_sell.dataobject;

import com.bob.wx_sell.enums.OrderStatusEnum;
import com.bob.wx_sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 09:17
 * @Description:
 */
@Entity
@DynamicUpdate
@Data
public class OrderMaster {

    @Id
    private String orderId;

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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

}
