package com.dm.enums;

import lombok.Getter;

@Getter
public enum  ResponseEnum {

    ERROR(-1, "服务端错误"),
    SUCCESS(0, "成功"),
    IMPORT_ERROR(1, "文件上传错误"),

    NEED_LOGIN(10, "用户未登录，请先登录"),
    USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),
    USERNAME_EXIST(12, "用户名已存在"),
    EMAIL_EXIST(13, "邮箱已存在"),
    PARAM_ERROR(14, "参数错误"),
    ;

    Integer code;
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
