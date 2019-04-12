package com.bob.wx_sell.service.impl;

import com.bob.wx_sell.converter.ConverterUtil;
import com.bob.wx_sell.dataobject.OrderDetail;
import com.bob.wx_sell.dataobject.OrderMaster;
import com.bob.wx_sell.dataobject.ProductInfo;
import com.bob.wx_sell.dto.CartDTO;
import com.bob.wx_sell.dto.OrderDTO;
import com.bob.wx_sell.enums.OrderStatusEnum;
import com.bob.wx_sell.enums.PayStatusEnum;
import com.bob.wx_sell.enums.ResultEnum;
import com.bob.wx_sell.exception.SellException;
import com.bob.wx_sell.repository.OrderDetailRepository;
import com.bob.wx_sell.repository.OrderMasterRepository;
import com.bob.wx_sell.service.OrderService;
import com.bob.wx_sell.service.PayService;
import com.bob.wx_sell.service.ProductService;
import com.bob.wx_sell.service.WebSocket;
import com.bob.wx_sell.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-25 10:48
 * @Description:
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private PayService payService;

    @Autowired
    private ProductService productService;
    @Autowired
    private WebSocket webSocket;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //生成订单Id
        String orderId = StringUtil.getUniqueKey();
        //设置商品价格
        BigDecimal productPrice = new BigDecimal(0);

        //1.查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.product_not_exist);
            }
            //2.计算总价
            productPrice = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(productPrice);

            //3.写入订单详情数据库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(StringUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            //此方法会覆盖所有值，包括null，所以要先copy，再赋值
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单数据库（OrderMaster和OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(productPrice);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(),
                        e.getProductQuantity())).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        //发送webSocket消息
        webSocket.sendMessage("有新订单");

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        //1.查询订单主表
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.order_not_exist);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.orderdetail_not_exist);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage =  orderMasterRepository.findAllByBuyerOpenid(pageable, "101010");
        /** 传统写法 **/
//        List<OrderMaster> orderMasterList = new ArrayList<>();
//        List<OrderDTO> orderDTOList = new ArrayList<>();
//
//        for (OrderMaster orderMaster:orderMasterPage){
//            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
//            OrderDTO orderDTO = new OrderDTO();
//            BeanUtils.copyProperties(orderMaster,orderDTO);
//            orderDTO.setOrderDetailList(orderDetailList);
//            orderDTOList.add(orderDTO);
//        }
//
//        Page<OrderDTO> orderDTOPage = new PageImpl(orderDTOList, pageable, orderMasterPage.getTotalElements());

        /** java8 lamoda写法 **/
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList = ConverterUtil.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {

        /** service中传输的数据，应该是controller封装好的，故直接把DTO作为依据，避免重复查询数据库 **/

        //1.查询订单是否存在
        //OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderDTO.getOrderId());
        OrderMaster orderMaster = new OrderMaster();
        if (orderDTO == null){
            throw new SellException(ResultEnum.order_not_exist);
        }
        //2.状态是否正确
        if (orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            throw new SellException(ResultEnum.order_status_error);
        }
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //3.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            throw new SellException(ResultEnum.order_update_error);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

        //4.返还库存
        //List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SellException(ResultEnum.order_detail_empty);
        }
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

        /** 传统写法 **/
        List<CartDTO> cartDTOList = new ArrayList<>();
//        for (OrderDetail orderDetail:orderDetailList){
//            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
//            CartDTO cartDTO = new CartDTO();
//            cartDTO.setProductId(productInfo.getProductId());
//            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
//        }
        /** java8写法 */
        cartDTOList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //5.若已支付，退换支付金额
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)){
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finnish(OrderDTO orderDTO) {
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.order_status_error);
        }

        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateOrder = orderMasterRepository.save(orderMaster);
        if(updateOrder==null){
            throw new SellException(ResultEnum.order_update_error);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.order_status_error);
        }
        //1.判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            throw new SellException(ResultEnum.pay_status_error);
        }

        //2.修改订单状态
        orderDTO.setPayStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateOrder = orderMasterRepository.save(orderMaster);
        if(updateOrder==null){
            throw new SellException(ResultEnum.order_update_error);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = ConverterUtil.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }
}
