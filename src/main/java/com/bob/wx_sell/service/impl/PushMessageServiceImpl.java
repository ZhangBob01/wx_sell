package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.config.WechatAccountConfig;
import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-27 21:01
 * @Description:
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {

        /**
         * 模版消息业务JDK开发
         * 1获取模版消息WxMpTemplateMessage
         * 2设置模版ID，setTemplateId和要发送给的用户setToUser
         * 3设置数据ist<WxMpTemplateData>
         */

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        //设置消息模版ID
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        //设置发送的用户
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "亲，请记得收货。"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18868812345"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        templateMessage.setData(wxMpTemplateDataList);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
            log.error("【微信模版消息】发送失败, {}", e);
        }
    }
}
