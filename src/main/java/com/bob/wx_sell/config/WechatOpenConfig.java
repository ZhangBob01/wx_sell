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
 * @Date: 2019-02-26 10:34
 * @Description:
 * 扫码登录开发步骤
 * 1。yml文件配置开放平台openAppId、openAppSecret
 * 2。创建微信账户配置文件WechatAccountConfig
 * 3。创建WechatOpenConfig类，开始验证
 *
 *
 */
@Component
public class WechatOpenConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){
        //1.新建WxMpService
        WxMpService wxOpenService = new WxMpServiceImpl();
        //2.设置配置
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());

        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();

        wxMpInMemoryConfigStorage.setAppId(wechatAccountConfig.getOpenAppId());
        wxMpInMemoryConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}
