package com.bob.wx_sell.service;

import com.bob.wx_sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-07 10:23
 * @Description:
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notifyHandle(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
