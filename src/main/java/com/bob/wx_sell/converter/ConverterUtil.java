package com.bob.wx_sell.converter;

import com.bob.wx_sell.dataobject.OrderDetail;
import com.bob.wx_sell.dataobject.OrderMaster;
import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-26 17:41
 * @Description:
 */
@Slf4j
public class ConverterUtil {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster,orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){

        List<OrderDTO> orderDTOList = new ArrayList<>();

        orderDTOList = orderMasterList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());

        return orderDTOList;
    }

    public static OrderDTO convert(OrderForm orderForm){

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        //谷歌gson工具,字符串转list
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());

        }catch (Exception e){
            log.error("【对象转换】错误，String={}",orderForm.getItems());
            throw new SellException(ResultEnum.param_error);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
