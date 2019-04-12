package com.bob.wx_sell.controller;

import com.bob.wx_sell.dataobject.ProductCategory;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.form.CategoryForm;
import com.bob.wx_sell.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Auther: toudaizhi
 * @Date: 2019-02-25 13:42
 * @Description:
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){

        //查询类目列表
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list");
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        ProductCategory productCategory = new ProductCategory();
        if(!StringUtils.isEmpty(categoryId)){
            productCategory = productCategoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("category/index");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/seller/category/index");
            return new ModelAndView("common/error");
        }
        ProductCategory productCategory = new ProductCategory();
        try{
            if(!StringUtils.isEmpty(categoryForm.getCategoryId())){
                productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            ProductCategory result = productCategoryService.save(productCategory);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/seller/category/index");
            return new ModelAndView("common/error");
        }
        map.put("msg","成功");
        map.put("url","/seller/category/list");
        return new ModelAndView("common/success");
    }
}
