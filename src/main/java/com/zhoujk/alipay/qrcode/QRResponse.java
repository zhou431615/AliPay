package com.zhoujk.alipay.qrcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRResponse {

    /**
     *  支付宝交易预创建响应
     */
    private QRCodeResponse alipay_trade_precreate_response;

    private String sign;
}
