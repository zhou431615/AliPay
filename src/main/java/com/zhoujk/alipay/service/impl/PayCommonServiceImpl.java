package com.zhoujk.alipay.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.zhoujk.alipay.entity.OrderDetail;
import com.zhoujk.alipay.mapper.OrderDetailMapper;
import com.zhoujk.alipay.service.PayCommonService;
import com.zhoujk.alipay.util.GenerateNum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:27
 */

@Service
public class PayCommonServiceImpl implements PayCommonService {
    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payproductcourse(JSONObject body, String userId, String orderNumber, String trade_no, String paymethod) {
        // 支付的课程
        String courseId = body.getString("courseId");
        // 支付的金额
        String money = body.getString("price");
        // 保存订单明细表
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(Long.valueOf(GenerateNum.generateOrder()));
        //        orderDetail.setUserid(userId);
        orderDetail.setCourseid(courseId);
        orderDetail.setUsername("靓仔");
        orderDetail.setPaymethod(paymethod);
        orderDetail.setCoursetitle(body.getString("courseTitle"));
        orderDetail.setOrdernumber(orderNumber);
        orderDetail.setTradeno(trade_no);
        orderDetail.setPrice(money == null ? "0.01" : money);
        orderDetailMapper.insert(orderDetail);
    }
}
