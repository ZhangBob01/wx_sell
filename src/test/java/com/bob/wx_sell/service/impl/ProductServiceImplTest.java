package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dataobject.ProductInfo;
import com.bob.wx_sell.enums.ProductStatusEnum;
import com.bob.wx_sell.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 15:40
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        System.out.println(productInfo.toString());
    }

    @Test
    public void findUpAll() {

        List<ProductInfo> productInfoList = productService.findUpAll();
        System.out.println(productInfoList);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,5);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("234567");
        productInfo.setProductName("吃鸡手柄");
        productInfo.setProductPrice(new BigDecimal(50));
        productInfo.setProductStock(10);
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setProductDescripttion("方便吃鸡走位");
        productInfo.setProductIcon("api.bob.com");
        productInfo.setCategoryType(3);
        productService.save(productInfo);
    }

    @Test
    public void onSale() {
        productService.onSale("1550741890426549552");
    }

    @Test
    public void offSale() {
        productService.offSale("234567");
    }

    @Test
    public void findOne1() {
        ProductInfo productInfo = productService.findOne("123456");
        System.out.println(productInfo);
    }
}