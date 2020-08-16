package com.dm.enums;

import lombok.Getter;

@Getter
public enum  ResponseEnum {

    ERROR(-1, "服务端错误"),
    SUCCESS(0, "成功"),
    IMPORT_ERROR(1, "文件上传错误"),

    NEED_LOGIN(10, "用户未登录，请先登录"),
    USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),
    ;

    Integer code;
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
