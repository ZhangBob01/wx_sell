package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 11:09
 * @Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
