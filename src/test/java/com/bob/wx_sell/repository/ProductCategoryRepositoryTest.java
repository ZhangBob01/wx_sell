package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-19 21:32
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne() {
        Optional<ProductCategory> productCategory = repository.findById(1);
        System.out.println(productCategory.toString());
        System.out.println(productCategory.get().getCategoryName());
    }

    @Test
    //测试中@Transactional注解，所有事物全部回滚
    @Transactional
    public void saveEntity(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("商务套餐榜");
        productCategory.setCategoryType(2);
        repository.save(productCategory);
    }
    @Test
    public void updateEntity(){
        ProductCategory productCategory = repository.findById(2).get();

        productCategory.setCategoryName("商务榜");
        productCategory.setCategoryType(2);
        repository.save(productCategory);
    }
    @Test
    public void deleteEntity(){
        repository.deleteById(2);
    }

    @Test
    public void findByCategoryList(){

        List<Integer> list = Arrays.asList(1,2,3);

        List<ProductCategory> productCategoryList = repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,productCategoryList.size());
    }
}