package com.bob.wx_sell.controller;

import com.bob.wx_sell.dataobject.ProductCategory;
import com.bob.wx_sell.dataobject.ProductInfo;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.form.ProductForm;
import com.bob.wx_sell.service.ProductCategoryService;
import com.bob.wx_sell.service.ProductService;
import com.bob.wx_sell.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * @Auther: toudaizhi
 * @Date: 2019-02-21 15:24
 * @Description:
 */
@Slf4j
@RequestMapping("/seller/product/")
@Controller
public class SellerProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;

    /**
     * 获取订单列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "2") Integer size,
                             Map<String,Object> map){

        Pageable pageable = PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageable);

        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("product/list",map);
    }

    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            log.error("【商品上架】发生异常：{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("msg","上架成功");
        map.put("url","/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            log.error("【商品下架】发生异常：{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("msg","下架成功");
        map.put("url","/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 商品详情页面跳转
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                                Map<String,Object> map){
        //查询商品
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);

        return new ModelAndView("product/product",map);
    }

    @PostMapping("/save")
    @CacheEvict(cacheNames = "product",key = "123")
    //@CachePut(cacheNames = "product",key = "123") 此注解，要求返回值和Cacheable返回值相同
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try{
            if(!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productService.findOne(productForm.getProductId());
            }else {
                productForm.setProductId(StringUtil.getUniqueKey());

            }

            BeanUtils.copyProperties(productForm,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/seller/product/list");
        map.put("msg","成功");
        return new ModelAndView("common/success",map);
    }
}
