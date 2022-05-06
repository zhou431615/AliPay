package com.zhoujk.alipay.service;

import com.zhoujk.alipay.entity.OrderTrade;
import com.zhoujk.alipay.vo.RefundVO;

/**
 * @author Zhoujiankang
 */
public interface OrderTradeService
{
    /**
     * @param refundVO
     * @return
     */
    //String getTradeNo(RefundVO refundVO);

    public OrderTrade selectOne(RefundVO refundVO);

    public boolean save(RefundVO refundVO);
}
