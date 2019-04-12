package com.bob.wx_sell.controller;

import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-14 11:13
 * @Description:
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    OrderService orderService;
    /**
     * 获取订单
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "2") Integer size,
                            Map<String,Object> map){

        PageRequest request = PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                                Map<String,Object> map){
        try{
            //获取订单信息
            OrderDTO orderDTO = orderService.findOne(orderId);
            OrderDTO result = orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "订单取消成功");
        map.put("url","/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            //获取订单信息
            orderDTO = orderService.findOne(orderId);

        }catch (SellException e){
            log.error("【卖家端订单详情】发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/order",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finnish")
    public ModelAndView finnish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try{
            //获取订单信息
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finnish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "订单完结成功");
        map.put("url","/seller/order/list");
        return new ModelAndView("common/success", map);
    }

}


