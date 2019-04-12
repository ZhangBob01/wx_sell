package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dataobject.OrderMaster;
import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.service.OrderService;
import com.bob.wx_sell.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-27 21:30
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1545806615922299652");
        pushMessageService.orderStatus(orderDTO);
    }
}