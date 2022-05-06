package com.zhoujk.alipay.result;

import com.alipay.api.AlipayApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : zhoujiankang
 * @Desc: 全局异常处理类
 * @since : 2022/5/3 23:06
 */
@ControllerAdvice
public class GlobalException
{
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e)
    {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(PayException.class)
    @ResponseBody
    public Result error(PayException e)
    {
        e.printStackTrace();
        return Result.fail();

    }

    @ExceptionHandler(AlipayApiException.class)
    @ResponseBody
    public Result error(AlipayApiException e)
    {
        e.printStackTrace();
        return Result.fail();
    }
}
