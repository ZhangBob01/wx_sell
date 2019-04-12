package com.bob.wx_sell.controller;

import com.bob.wx_sell.VO.ResultVO;
import com.bob.wx_sell.converter.ConverterUtil;
import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.form.OrderForm;
import com.bob.wx_sell.service.OrderService;
import com.bob.wx_sell.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-01-02 10:39
 * @Description:
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
@Api("Swagger订单控制器")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    //创建订单
    @PostMapping("/caeateOrder")
    @ApiOperation(value="创建订单接口")
    public ResultVO<Map<String,String>> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult){

        //参数验证
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.param_error.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = ConverterUtil.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.cart_empty);
        }
        OrderDTO newOrder = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",newOrder.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】参数不正确，openid为空");
            throw new SellException(ResultEnum.openid_empty);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);

        //接口中字段无数据，又不能返回null，则赋初始值解决
        return ResultVOUtil.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){

        OrderDTO orderDTO =checkOrderOwner(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){


        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        orderService.cancel(orderDTO);

        return ResultVOUtil.success();
    }

    private OrderDTO checkOrderOwner(String openid,String orderId){

        if(StringUtils.isEmpty(openid)){
            log.error("【订单检查】openid为null或者''，openid={}",openid);
            throw new SellException(ResultEnum.openid_empty);
        }
        if(StringUtils.isEmpty(orderId)){
            log.error("【订单检查】orderId为null或者''，orderId={}",orderId);
            throw new SellException(ResultEnum.order_id_empty);
        }
        OrderDTO orderDTO =orderService.findOne(orderId);
        if(orderDTO == null){
            log.error("【订单检查】订单不存在，orderId={}",orderId);
            throw new SellException(ResultEnum.orderdetail_not_exist);
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【订单检查】，用户openid和订单openid不一致，orderId={}，openid={}",orderId,openid);
            throw new SellException(ResultEnum.openid_owner_error);
        }

        return orderDTO;
    }


}
