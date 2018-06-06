package com.myself.sbdiancan.service.impl;

import com.myself.sbdiancan.config.WechatAccountConfig;
import com.myself.sbdiancan.dto.OrderDTO;
import com.myself.sbdiancan.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 12:50 2018\6\6 0006
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    /**
     * 正常发送，还需在公众号配置IP白名单
     * @param orderDTO
     */
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));//公众号生成的模板id
        templateMessage.setToUser(orderDTO.getBuyerOpenid());//用户相对于公众号的openid
        //模板数据填充
        List<WxMpTemplateData> data = Arrays.asList(
            new WxMpTemplateData("first","亲，请记得收货"),
            new WxMpTemplateData("keyword1","微信点餐"),
            new WxMpTemplateData("keyword2","1880000000"),
            new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
            new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
            new WxMpTemplateData("keyword5","￥" + orderDTO.getOrderAmount()),
            new WxMpTemplateData("remark","欢迎再次光临！")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e){
            log.error("【微信模板消息】发送失败，{}",e);
        }

    }
}
