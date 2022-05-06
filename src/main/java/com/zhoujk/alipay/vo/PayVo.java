package com.zhoujk.alipay.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayVo {

    private String courseId;

    private Integer payMethod;

}
