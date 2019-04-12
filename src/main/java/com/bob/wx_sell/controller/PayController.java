package com.bob.wx_sell.controller;

import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.service.OrderService;
import com.bob.wx_sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-07 10:08
 * @Description:
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView creat(@RequestParam("orderId") String orderId,
                              @RequestParam("returnUrl") String returnUrl){
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.order_not_exist);
        }

        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        ModelAndView modelAndView = new ModelAndView("pay/create",map);

        return modelAndView;
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){

        payService.notifyHandle(notifyData);
        return new ModelAndView("pay/success");
    }
}
