package com.myself.sbdiancan.service;

import com.myself.sbdiancan.dto.OrderDTO;

/**
 * 模板消息推送
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 12:47 2018\6\6 0006
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);

}
