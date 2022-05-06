package com.zhoujk.alipay.qrcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:04
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QRCodeResponse {
    /**
     * 返回的状态码
     */
    private String code;

    /**
     * 返回的信息
     */
    private String msg;

    /**
     * 交易的流水号
     */
    private String out_trade_no;

    /**
     * 生成二维码的内容
     */
    private String qr_code;

}
