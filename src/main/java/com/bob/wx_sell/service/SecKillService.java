package com.bob.wx_sell.service;

import org.springframework.stereotype.Service;

/**
 * @Auther: toudaizhi
 * @Date: 2019-03-03 13:49
 * @Description:
 */
@Service
public interface SecKillService {
    /**
     * 查询商品剩余量
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    void orderProductMockDiffUser(String productId);
}
