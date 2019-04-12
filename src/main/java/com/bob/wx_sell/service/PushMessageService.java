package com.bob.wx_sell.service;

import com.bob.wx_sell.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-27 20:59
 * @Description:
 */
@Service
public interface PushMessageService {
    /**
     * 订单状态变更消息
     */
    void orderStatus(OrderDTO orderDTO);
}
