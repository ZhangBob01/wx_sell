package com.bob.wx_sell.service;

import com.bob.wx_sell.dataobject.ProductInfo;
import com.bob.wx_sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 14:02
 * @Description:
 */
@Service
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 商品上架
     * @param orderId
     * @return
     */
    ProductInfo onSale(String orderId);
    /**
     * 商品下架
     * @param orderId
     * @return
     */
    ProductInfo offSale(String orderId);


}
