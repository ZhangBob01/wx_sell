package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.service.RedisLock;
import com.bob.wx_sell.service.SecKillService;
import com.bob.wx_sell.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-03-03 14:00
 * @Description:
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    private static final int OUTTIME = 10*1000;
    @Autowired
    private RedisLock redisLock;

    static Map<String,Integer> products;
    static Map<String,String> orders;
    static Map<String,Integer> stock;

    static{
        products = new HashMap<>();
        orders = new HashMap<>();
        stock = new HashMap<>();

        products.put("123456",10000);
        stock.put("123456",10000);
    }

    private String queryMap(String productId){

        return "国庆活动，商品降价，限量份"
                + products.get(productId)
                +" 还剩：" + stock.get(productId)+" 份"
                +" 该商品成功下单用户数目："
                +  orders.size() +" 人" ;
    }
    @Override
    public String querySecKillProductInfo(String productId) {

        return queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {

        //1.加锁
        long time = System.currentTimeMillis()+OUTTIME;
        if(!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"人太多了，换个姿势试一下～～");
        }

        //2.模拟秒杀业务：
        // a查询库存
        int stockNum = stock.get(productId);
        if(stockNum <= 0) {
            throw new SellException(100,"活动结束");
        }
        else {
            //b.模拟下单
            orders.put(StringUtil.getUniqueKey(),productId);
            //c.减库存
            stockNum--;
            try {
                Thread.sleep(100);
            } catch (Exception e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }

        //3.解锁
        redisLock.unlock(productId,String.valueOf(time));
    }
}
