package com.myself.sbdiancan.controller;

import com.lly835.bestpay.rest.type.Get;
import com.myself.sbdiancan.dataobject.ProductInfo;
import com.myself.sbdiancan.dto.OrderDTO;
import com.myself.sbdiancan.enums.ResultEnum;
import com.myself.sbdiancan.exception.SbException;
import com.myself.sbdiancan.form.ProductForm;
import com.myself.sbdiancan.service.OrderService;
import com.myself.sbdiancan.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * 卖家端订单
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:20 2018\6\4 0004
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SbOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页，从第一页开始
     * @param size 一页多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String, Object> map){
        PageRequest request = new PageRequest(page - 1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);

            orderService.cancel(orderDTO);

        } catch (SbException e){
            log.error("【卖家端取消订单】发生异常{}",e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sb/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sb/seller/order/list");
        return new ModelAndView("common/success",map);
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
        OrderDTO orderDTO =new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SbException e){
            log.error("【卖家端查询订单详情】发生异常{}",e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sb/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);

        return new ModelAndView("order/detail",map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                                 Map<String,Object> map){
        OrderDTO orderDTO =new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SbException e){
            log.error("【卖家端完结订单】发生异常{}",e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sb/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sb/seller/order/list");
        return new ModelAndView("common/success",map);
    }

}
