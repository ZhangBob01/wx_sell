package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dataobject.ProductInfo;
import com.bob.wx_sell.dto.CartDTO;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.repository.ProductInfoRepository;
import com.bob.wx_sell.enums.ProductStatusEnum;
import com.bob.wx_sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 14:14
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exist);
            }
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();

            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exist);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if(result < 0){
                throw new SellException(ResultEnum.product_stock_error);
            }

            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String orderId) {
        ProductInfo productInfo = repository.findById(orderId).get();
        if(productInfo==null){
            throw new SellException(ResultEnum.product_not_exist);
        }
        if(productInfo.getProductStatus()==ProductStatusEnum.DOWN.getCode()){
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            productInfo = repository.save(productInfo);
        }
        return productInfo;
    }

    @Override
    public ProductInfo offSale(String orderId) {

        ProductInfo productInfo = repository.findById(orderId).get();
        if(productInfo==null){
            throw new SellException(ResultEnum.product_not_exist);
        }
        if(productInfo.getProductStatus()==ProductStatusEnum.UP.getCode()){
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
            productInfo = repository.save(productInfo);
        }
        return productInfo;
    }


}
