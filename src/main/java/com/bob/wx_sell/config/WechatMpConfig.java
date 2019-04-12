package com.bob.wx_sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-05 16:28
 * @Description:
 */
@Component
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Bean
    public WxMpService wxMpService(){

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();

        wxMpConfigStorage.setAppId(wechatAccountConfig.getMpAppId());//设置AppID，从配置文件中获取
        wxMpConfigStorage.setSecret(wechatAccountConfig.getMpAppSecret());//设置secret，从配置文件中获取
        return wxMpConfigStorage;
    }
}
