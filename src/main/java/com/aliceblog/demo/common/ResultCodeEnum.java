package com.aliceblog.demo.common;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 20000, "成功"),
    UNKNOWN_ERROR(false, 20001, "未知错误"),
    PARAM_ERROR(false, 20002, "参数错误"),
    ACCOUNT_NOT_EXIST(false,20003, "账号不存在"),
    DUPLICATE_ACCOUNT(false, 20004,"账号重复"),
    ACCOUNT_IS_DISABLED(false,20005 ,"账号被禁用"),
    INCORRECT_CREDENTIALS(false,20006 ,"账号或密码错误"),
    NOT_LOGIN_IN(false, 20007,"账号未登录"),
    UNAUTHORIZED(false, 20008,"没有权限")
    ;

    // 响应是否成功
    private final Boolean success;
    // 响应状态码
    private final Integer code;
    // 响应信息
    private final String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}
