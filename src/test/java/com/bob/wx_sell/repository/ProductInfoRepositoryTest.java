package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.ProductInfo;
import com.bob.wx_sell.utils.StringUtil;
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
 * @Date: 2018-12-21 13:30
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveProduct(){
        System.out.println(UUID.randomUUID().toString());
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(StringUtil.getUniqueKey());
        productInfo.setProductName("测试商品二");
        productInfo.setProductPrice(new BigDecimal(80));
        productInfo.setProductStock(10);
        productInfo.setProductStatus(0);
        productInfo.setProductDescripttion("测试描述二");
        productInfo.setProductIcon("api.bob.com");
        productInfo.setCategoryType(3);
        repository.save(productInfo);
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        for (ProductInfo productInfo:productInfoList){
            System.out.println(productInfo);
        }
    }

    @Test
    public void findById(){
        ProductInfo productInfo = repository.findByProductId("1550741890426549552");
        System.out.println(productInfo);
    }
}