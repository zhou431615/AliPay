package com.zhoujk.alipay.service;

import com.alipay.api.AlipayApiException;
import com.zhoujk.alipay.entity.OrderTrade;
import com.zhoujk.alipay.vo.PayVo;
import com.zhoujk.alipay.vo.RefundVO;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Zhoujiankang
 */
public interface AlipayService {

    /**
     * 获取支付宝支付二维码
     * @param payVo
     * @Exception 异常
     * @return img
     */
    byte[] alipay(PayVo payVo) throws Exception;


    /**
     * 支付成功后的回调函数
     *
     * @param request 请求
     * @return
     * @Exception 异常
     */
    Boolean alipayCallback(HttpServletRequest request) throws Exception;


    boolean refund(OrderTrade orderTrade);


    public String query(String out_trade_no);

}
