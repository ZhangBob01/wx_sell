package com.bob.wx_sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-05 16:40
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /** 公众平台ID. */
    private String mpAppId;
    /** 公众平台密钥. */
    private String mpAppSecret;
    /** 客户号. */
    private String mchId;
    /** 客户密钥. */
    private String mchKey;
    /** 商户证书路径. */
    private String keyPath;
    /** 微信支付异步通知地址. */
    private String notifyUrl;
    /** 重定向地址. */
    private String reUrl;
    /** 开放平台ID. */
    private String openAppId;
    /** 开放平台密钥. */
    private String openAppSecret;
    /** 消息模版ID. */
    private Map<String,String> templateId;


}
