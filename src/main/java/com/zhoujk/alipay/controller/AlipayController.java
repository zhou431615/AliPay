package com.zhoujk.alipay.controller;

import com.zhoujk.alipay.entity.OrderTrade;
import com.zhoujk.alipay.result.Result;
import com.zhoujk.alipay.service.AlipayService;
import com.zhoujk.alipay.service.OrderTradeService;
import com.zhoujk.alipay.vo.PayVo;
import com.zhoujk.alipay.vo.RefundVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:14
 */
@Api(tags = "支付宝接口")
@RestController
public class AlipayController
{

    @Autowired private AlipayService alipayService;

    @Autowired private OrderTradeService orderTradeService;

    /**
     * 拉起支付请求
     *
     * @param payVo
     * @return
     * @throws Exception
     */
    @GetMapping("/alipay/pay")
    public byte[] alipay(PayVo payVo) throws Exception
    {
        byte[] alipay = alipayService.alipay(payVo);
        return alipay;
    }

    /**
     * 支付成功以后的异步回调API
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/alipay/pay/callback")
    public String notify_url(HttpServletRequest request) throws Exception
    {
        Boolean result = alipayService.alipayCallback(request);
        if (result) {
            return "success";
        } else {
            return "false";
        }
    }

    @PostMapping("/alipay/refund")
    public Result refund(@RequestBody(required = true) RefundVO refundVO)
    {
        String tradeNo = alipayService.query(refundVO.getOut_trade_no());
        OrderTrade orderTrade = new OrderTrade();
        orderTrade.setOutTradeNO(refundVO.getOut_trade_no());
        orderTrade.setTradeNo(tradeNo);
        orderTrade.setTotalAmount(refundVO.getRefund_amount());

        boolean result = alipayService.refund(orderTrade);
        return Result.ok();


    }
}

