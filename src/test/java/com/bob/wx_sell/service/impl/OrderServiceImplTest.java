package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dataobject.OrderDetail;
import com.bob.wx_sell.dataobject.OrderMaster;
import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.enums.OrderStatusEnum;
import com.bob.wx_sell.enums.PayStatusEnum;
import com.bob.wx_sell.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-26 10:02
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "ojQ0U5ixl2T76iQXQ-emvuyqtqrQ";
    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("北京朝阳立水桥");
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("12345678910");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail detail1 = new OrderDetail();
        detail1.setProductId("123456");
        detail1.setProductName("吃鸡神器");
        detail1.setProductQuantity(10);
        detail1.setProductPrice(new BigDecimal(50));
        detail1.setProductIcon("api.bob.com");
        orderDetailList.add(detail1);

        OrderDetail detail2 = new OrderDetail();
        detail2.setProductId("234567");
        detail2.setProductName("吃鸡手柄");
        detail2.setProductQuantity(10);
        detail2.setProductPrice(new BigDecimal(50));
        detail2.setProductIcon("api.bob.com");
        orderDetailList.add(detail2);
        orderDTO.setOrderDetailList(orderDetailList);
        orderService.create(orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1545806615922299652");
        System.out.println(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<OrderDTO> orderDTOPage = orderService.findList("101010",pageRequest);
        System.out.println(orderDTOPage.getTotalElements());
        for (OrderDTO orderDTO:orderDTOPage){
            System.out.println(orderDTO);
        }
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1545806615922299652");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finnish() {
        OrderDTO orderDTO = orderService.findOne("1545806615922299652");
        OrderDTO result = orderService.finnish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1545806615922299652");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());

    }
}