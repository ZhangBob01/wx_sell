package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-25 23:17
 * @Description:
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findBySellerId(String sellerId);

    SellerInfo findByOpenid(String openid);
}