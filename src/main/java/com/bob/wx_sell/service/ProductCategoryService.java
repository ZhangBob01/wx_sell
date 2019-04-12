package com.bob.wx_sell.service;

import com.bob.wx_sell.dataobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-20 15:12
 * @Description:
 */
@Service
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    ProductCategory update(ProductCategory productCategory);
}
