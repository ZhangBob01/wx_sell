package com.bob.wx_sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-26 14:47
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {
    /** 微信公众平台授权url. */
    private String wechatMpAuthorize;

    /** 微信开放平台授权url. */
    private String wechatOpenAuthorize;

    /** 点餐系统. */
    private String sell;

}
