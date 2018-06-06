package com.myself.sbdiancan.service.impl;

import com.myself.sbdiancan.dto.OrderDTO;
import com.myself.sbdiancan.enums.ResultEnum;
import com.myself.sbdiancan.exception.SbException;
import com.myself.sbdiancan.service.BuyerService;
import com.myself.sbdiancan.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 20:58 2018\6\3 0003
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("【取消订单】查不到该订单，orderId={}",orderId);
            throw new SbException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        //判断是否属于自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致，openid={},orderDTO={}",openid,orderDTO);
            throw new SbException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
