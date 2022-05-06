package com.zhoujk.alipay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhoujk.alipay.entity.OrderTrade;
import com.zhoujk.alipay.mapper.OrderTradeMapper;
import com.zhoujk.alipay.service.OrderTradeService;
import com.zhoujk.alipay.vo.RefundVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/5 13:42
 */
@Service
public class OrderTradeServiceImpl implements OrderTradeService
{
    @Resource private OrderTradeMapper orderTradeMapper;

    @Override
    public OrderTrade selectOne(RefundVO refundVO)
    {
        QueryWrapper<OrderTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", refundVO.getTrade_no());
        OrderTrade orderTrade = orderTradeMapper.selectOne(queryWrapper);
        return orderTrade;
    }

    @Override
    public boolean save(RefundVO refundVO)
    {
        OrderTrade orderTrade = new OrderTrade();
        orderTrade.setTradeNo(refundVO.getTrade_no());
        orderTrade.setOutTradeNO(refundVO.getOut_trade_no());
        orderTrade.setTotalAmount(refundVO.getRefund_amount());
        int result = orderTradeMapper.insert(orderTrade);
        return result > 0;
    }
}
