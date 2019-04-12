package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.service.OrderService;
import com.bob.wx_sell.service.PayService;
import com.bob.wx_sell.utils.JsonUtil;
import com.bob.wx_sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-07 10:25
 * @Description:
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("自定义订单名称");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付，request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，response={}", JsonUtil.toJson(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notifyHandle(String notifyData) {

        //支付验证步骤
        //1.验证签名
        //2.验证支付状态
        //前两步SDK已完成
        //3.支付金额判断
        //4.支付人判断，根据业务逻辑，下单人==支付人
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}", JsonUtil.toJson(payResponse));

        //修改支付状态
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if(orderDTO == null){
            log.error("【微信支付】异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.order_not_exist);
        }

        //判断订单金额是否一致
        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知，订单金额不一致，orderId={},微信通知金额={},系统金额={}",
                    orderDTO.getOrderId(),payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.wxpay_notify_money_verify_error);
        }
        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {

        //需要添加证书
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}",JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}",JsonUtil.toJson(refundResponse));

        return refundResponse;
    }

}
