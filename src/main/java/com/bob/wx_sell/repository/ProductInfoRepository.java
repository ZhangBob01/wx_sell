package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 13:16
 * @Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo findByProductId(String productId);
}
