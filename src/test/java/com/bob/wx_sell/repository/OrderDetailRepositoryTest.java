package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 14:54
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId(){
        List<OrderDetail> orderDetailList = repository.findByOrderId("55cb7eea96bc4b0197e45d0d902f4121");
        Assert.assertEquals(1,orderDetailList.size());
    }

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(UUID.randomUUID().toString().replace("-",""));
        orderDetail.setOrderId("55cb7eea96bc4b0197e45d0d902f4121");
        orderDetail.setProductIcon("http://api.bob.com");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("吃鸡神器");
        orderDetail.setProductPrice(new BigDecimal(50));
        orderDetail.setProductQuantity(2);
        repository.save(orderDetail);
    }

}