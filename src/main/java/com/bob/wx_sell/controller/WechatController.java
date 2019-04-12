package com.bob.wx_sell.controller;

import com.bob.wx_sell.config.ProjectUrlConfig;
import com.bob.wx_sell.config.WechatAccountConfig;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-04 16:49
 * @Description: MP_OAuth2网页授权
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpService wxOpenService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){

        //1.设置配置
        //2.方法调用:首先，构造网页授权url，然后构成超链接让用户点击
        //其次，当用户同意授权后，会回调所设置的url并把authorization code传过来，
        // 然后用这个code获得access token，其中也包含用户的openid等信息（下边getUserInfo方法就是根据code获取openoid）
        String reUrl = projectUrlConfig.getWechatMpAuthorize()+"/wechat/getUserInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl
                (reUrl, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    //上边所填回调地址响应方法
    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("code") String code,
                            @RequestParam("state") String returnUrl){

        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken =
                    wxMpService.oauth2getAccessToken(code);

            String openId = wxMpOAuth2AccessToken.getOpenId();
            log.info(returnUrl + "?openid=" + openId);
            return "redirect:" + returnUrl + "?openid=" + openId;
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.wechat_mp_error.getCode(),e.getError().getErrorMsg());
        }
    }

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl){

        String reUrl = projectUrlConfig.getWechatOpenAuthorize()+"/wechat/getQrUserinfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(reUrl,
                WxConsts.QrConnectScope.SNSAPI_LOGIN,
                URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/getQrUserinfo")
    public String getQrUserinfo(@RequestParam("code") String code,
                                @RequestParam("state") String returnUrl){
        try{
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();
            log.info(returnUrl + "?openid=" + openId);
            return "redirect:" + returnUrl +"?openid=" + openId;
        }catch (WxErrorException e){
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.wechat_mp_error.getCode(),e.getError().getErrorMsg());
        }
    }
}
