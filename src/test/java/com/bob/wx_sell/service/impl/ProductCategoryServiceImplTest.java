package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dataobject.ProductCategory;
import com.bob.wx_sell.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-20 23:00
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOne() {

        ProductCategory productCategory = productCategoryService.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        for(ProductCategory productCategory:productCategoryList){
            System.out.println("nnnnn");
            System.out.println(productCategory.toString());
        }
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2);
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(list);
        for(ProductCategory productCategory:productCategoryList){
            System.out.println("yyyyyyyyy");
            System.out.println(productCategory.toString());
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("女生最爱",3);
        productCategoryService.save(productCategory);

    }

    @Test
    public void update() {


    }
}