package com.bob.wx_sell.utils;

import com.bob.wx_sell.dataobject.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-07 15:03
 * @Description:
 */
public class JsonUtil {
    /**
     * json工具
     * @param object
     * @return
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(object);
    }

    public static void main(String[] args){
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail detail1 = new OrderDetail();
        detail1.setProductId("1550741890426549552");
        detail1.setProductName("测试商品一");
        detail1.setProductQuantity(1);
        detail1.setProductPrice(new BigDecimal(50));
        detail1.setProductIcon("api.bob.com");
        orderDetailList.add(detail1);

        OrderDetail detail2 = new OrderDetail();
        detail2.setProductId("1550741965345839506");
        detail2.setProductName("测试商品二");
        detail2.setProductQuantity(1);
        detail2.setProductPrice(new BigDecimal(80));
        detail2.setProductIcon("api.bob.com");
        orderDetailList.add(detail2);
        System.out.println(toJson(orderDetailList));
    }
}
