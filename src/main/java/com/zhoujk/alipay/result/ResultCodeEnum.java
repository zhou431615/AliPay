package com.zhoujk.alipay.result;

import lombok.Getter;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:07
 */

@Getter
public enum ResultCodeEnum {


    /**
     * 统一返回结果状态
     */
    SUCCESS(20000,"成功"),
    FAIL(19999,"失败"),
    UNKNOWN_REASON( 20001, "未知错误"),

    LOGIN_PHONE_ERROR( 20002, "手机号码不能为空"),
    ACCOUNT_PHONE_ERROR( 20002, "账号信息不能为空"),
    LOGIN_PHONE_PATTERN_ERROR( 20003, "手机号码格式不正确"),
    VALIDATION_CODE_ERROR( 20004, "验证码不正确"),
    LOGIN_CODE_ERROR( 20005, "短信验证码不能为空"),
    LOGIN_CAPATH_ERROR( 20006, "图形验证码不能为空"),
    LOGIN_CODE_FAIL_ERROR( 20007, "短信验证码失效，请重新发送"),
    LOGIN_CODE_INPUT_ERROR( 20008, "输入的短信码有误"),
    PHONE_ERROR_MSG( 20009, "该手机号未绑定账户"),
    USER_FORBIDDEN( 20010, "该用户已被禁用，请联系平台客服"),
    LOGIN_PWD_ERROR( 200011, "密码不允许为空"),
    LOGIN_PWD_INPUT_ERROR( 200012, "密码输入有误"),
    LOGIN_PWD_NO_INPUT_ERROR( 200013, "检测到没有完善密码信息"),

    BAD_SQL_GRAMMAR( 21001, "sql语法错误"),
    JSON_PARSE_ERROR( 21002, "json解析异常"),
    PARAM_ERROR( 21003, "参数不正确"),
    USER_PWD_ERROR( 21003, "尚未找到对应的用户信息"),
    SYSTEM_EXCEPTION(20020,"系统异常"),


    PAY_TRADE_ERROR(22001,"支付异常"),
    PAY_REFUND_ERROR(22002,"退款异常");


    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
