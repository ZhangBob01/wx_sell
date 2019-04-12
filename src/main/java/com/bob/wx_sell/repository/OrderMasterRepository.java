package com.bob.wx_sell.repository;

import com.bob.wx_sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-24 09:43
 * @Description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**
     * 根据openid查询用户订单
     * @param openid
     * @return
     */
    List<OrderMaster> findByBuyerOpenid(String openid);

    /**
     * 根据openid分页查询订单数据
     * @param pageable
     * @param openid
     * @return
     */
    Page<OrderMaster> findAllByBuyerOpenid(Pageable pageable, String openid);

    /**
     * 根据phone查询用户订单
     * @param buyerPhone
     * @return
     */
    List<OrderMaster> findByBuyerPhone(String buyerPhone);

    OrderMaster findByOrderId(String orderId);
}
