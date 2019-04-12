package com.bob.wx_sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-25 23:02
 * @Description:
 */
@Data
@Entity
@DynamicUpdate
public class SellerInfo {

    @Id
    private String sellerId;

    /** 用户名. */
    private String username;
    /** 密码. */
    private String password;
    /** openid. */
    private String openid;
}
