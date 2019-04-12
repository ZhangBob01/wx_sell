package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dataobject.SellerInfo;
import com.bob.wx_sell.repository.SellerInfoRepository;
import com.bob.wx_sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-26 21:20
 * @Description:
 */
@Slf4j
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findOne(String sellerId) {
        return sellerInfoRepository.findBySellerId(sellerId);
    }

    @Override
    public SellerInfo findSellerByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
