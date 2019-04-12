package com.bob.wx_sell.controller;

import com.bob.wx_sell.VO.ProductInfoVO;
import com.bob.wx_sell.VO.ProductVO;
import com.bob.wx_sell.VO.ResultVO;
import com.bob.wx_sell.dataobject.ProductCategory;
import com.bob.wx_sell.dataobject.ProductInfo;
import com.bob.wx_sell.service.ProductCategoryService;
import com.bob.wx_sell.service.ProductService;
import com.bob.wx_sell.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-21 17:04
 * @Description:
 */
@RestController
@Api("Swagger商品控制器")
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @ApiOperation(value="获取商品列表接口")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list(){

        //1.查询上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目
        List<Integer> categoryList = new ArrayList<>();
        //传统做法
//        for (ProductInfo productInfo:productInfoList){
//            categoryList.add(productInfo.getCategoryType());
//        }
        //Java8 lambda
        categoryList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryList);

        //3.封装数据

        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            //添加类目
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            //添加商品
            for (ProductInfo productInfo:productInfoList){
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo,productInfoVO);
                productInfoVOList.add(productInfoVO);
            }
            productVO.setFoods(productInfoVOList);
            productVOList.add(productVO);

        }


        return ResultVOUtil.success(productVOList);
    }
}
