package com.bob.wx_sell.dataobject.mapper;

import com.bob.wx_sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-28 19:57
 * @Description:
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","男生最爱TTTT");
        map.put("categoryType",110);
        mapper.insertByMap(map);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(100);
        productCategory.setCategoryName("周末榜");
        mapper.insertByObject(productCategory);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory productCategory = mapper.findByCategoryType(1);
        System.out.println(productCategory);
    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> productCategoryList = mapper.findByCategoryName("最爱");
        for(ProductCategory productCategory:productCategoryList){
            System.out.println(productCategory);
        }
    }

    @Test
    public void findAll(){
        PageRequest request = PageRequest.of(1, 10);
        List<ProductCategory> productCategoryList = mapper.findAll(request);
        System.out.println(productCategoryList.size());
    }

    @Test
    public void updateByCategoryType(){
        mapper.updateByCategoryType("商务榜",2);
    }
    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("商务套餐榜");
        productCategory.setCategoryType(2);
        mapper.updateByObject(productCategory);
    }

    @Test
    public void deleteByCategoryType(){
        mapper.deleteByCategoryType(110);
    }

    @Test
    public void findProductCategoryList(){
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        List<ProductCategory> productCategoryList = mapper.findProductCategoryList(integerList);

        for (ProductCategory productCategory:productCategoryList){
            System.out.println(productCategory);
        }
    }

    @Test
    public void selectByCategoryType(){
        ProductCategory productCategory = mapper.selectByCategoryType(1);
        System.out.println(productCategory);
    }
}