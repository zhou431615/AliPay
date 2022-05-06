package com.zhoujk.alipay.controller;

import com.zhoujk.alipay.config.AlipayConfig;
import com.zhoujk.alipay.result.Result;
import com.zhoujk.alipay.result.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/4 9:39
 */

@RestController
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AlipayConfig alipayConfig;

    @GetMapping("/test")
    public Result test(){
        //logger.info(alipayConfig.toString());

        return Result.fail(ResultCodeEnum.LOGIN_CODE_ERROR);
    }
}
