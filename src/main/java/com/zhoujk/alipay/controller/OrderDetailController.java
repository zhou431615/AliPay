package com.zhoujk.alipay.controller;

import com.zhoujk.alipay.service.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:40
 */

@RestController
@RequestMapping("OrderDetail")
public class OrderDetailController
{
    /**
     * 订单服务对象
     */
    @Resource private OrderDetailService orderDetailService;

}
