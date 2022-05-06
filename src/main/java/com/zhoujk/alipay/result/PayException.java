package com.zhoujk.alipay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:06
 */

@ApiModel(value = "自定义全局异常")
@Data
public class PayException extends RuntimeException
{

    @ApiModelProperty(value = "异常状态码") private Integer code;


    /**
     * 过状态码和错误消息创建异常对象
     *
     * @param code    code
     * @param message message
     */
    public PayException(Integer code, String message)
    {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum 枚举类型对象
     */

    public PayException(ResultCodeEnum resultCodeEnum)
    {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString()
    {
        return "PayException{" + "code=" + code + ",message=" + this.getMessage() + '}';
    }
}
