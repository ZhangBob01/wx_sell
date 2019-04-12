package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.dataobject.ProductCategory;
import com.bob.wx_sell.repository.ProductCategoryRepository;
import com.bob.wx_sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-20 15:31
 * @Description:
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        ProductCategory productCategory = repository.findById(categoryId).get();
        return productCategory;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> productCategoryList = repository.findAll();
        return productCategoryList;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> productCategoryList = repository.findByCategoryTypeIn(categoryTypeList);
        return productCategoryList;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {

        return repository.save(productCategory);
    }

    @Override
    public ProductCategory update(ProductCategory productCategory) {
        return null;
    }
}
