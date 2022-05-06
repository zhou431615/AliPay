package com.zhoujk.alipay.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 21:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundVO
{
    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 支付宝交易号
     */
    private String trade_no;


    /**
     * 订单金额
     */
    private Long refund_amount;

    /**
     *  订单名称/标题
     */
    //private String title;

}
