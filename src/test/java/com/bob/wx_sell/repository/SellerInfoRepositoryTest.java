package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.SellerInfo;
import com.bob.wx_sell.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Auther: toudaizhi
 * @Date: 2019-02-25 23:20
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(StringUtil.getUniqueKey());
        sellerInfo.setOpenid("112233");
        sellerInfo.setPassword("123456");
        sellerInfo.setUsername("zhangsan");
        repository.save(sellerInfo);

    }

    @Test
    public void findone(){
       SellerInfo sellerInfo = repository.findBySellerId("1551146587170527068") ;
       System.out.println(sellerInfo);
    }

    @Test
    public void findSeller(){
        SellerInfo sellerInfo = repository.findByOpenid("112233");
        System.out.println(sellerInfo);
    }

}