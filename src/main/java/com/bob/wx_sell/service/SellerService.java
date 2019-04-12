package com.bob.wx_sell.service;

import com.bob.wx_sell.dataobject.SellerInfo;
import org.springframework.stereotype.Service;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-26 21:18
 * @Description:
 */
@Service
public interface SellerService {

    SellerInfo findOne(String sellerId);

    SellerInfo findSellerByOpenid(String openid);
}
