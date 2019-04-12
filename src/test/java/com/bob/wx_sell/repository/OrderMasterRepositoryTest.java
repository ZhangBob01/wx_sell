package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 14:07
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByBuyerOpenid(){
        List<OrderMaster> orderMasterList = repository.findByBuyerOpenid("123");
        Assert.assertEquals(1,orderMasterList.size());
    }

    @Test
    public void findByBuyerPhone(){
        List<OrderMaster> orderMasterList = repository.findByBuyerPhone("12345678910");
        Assert.assertEquals(1,orderMasterList.size());
    }

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        orderMaster.setBuyerAddress("朝阳区 光华路6号 汉威大厦");
        orderMaster.setBuyerName("李四");
        orderMaster.setBuyerOpenid("124");
        orderMaster.setBuyerPhone("12345678911");
        orderMaster.setOrderAmount(new BigDecimal(100));
        orderMaster.setOrderStatus(1);
        orderMaster.setPayStatus(1);
        repository.save(orderMaster);
    }

    @Test
    public void findAll(){
        PageRequest request = PageRequest.of(0,1);

        Page<OrderMaster> orderMasterPage = repository.findAllByBuyerOpenid(request,"123");
        System.out.println(orderMasterPage.getTotalElements());
        System.out.println(orderMasterPage.getTotalPages());
    }

    @Test
    public void findOne(){
        OrderMaster orderMaster = repository.findByOrderId("1545806615922299652");
        System.out.println(orderMaster);
    }
}