package com.zhoujk.alipay.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Zhoujiankang
 */
public interface PayCommonService {
    /**
     * 添加订单
     * @param body
     * @param s
     * @param orderNumber
     * @param trade_no
     * @param s1
     */
    void payproductcourse(JSONObject body, String s, String orderNumber, String trade_no, String s1);
}
