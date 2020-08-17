package com.dm.enums;


import lombok.Getter;

/**
 * 角色：2-最高级管理员，1-管理员，0-普通用户
 */
@Getter
public enum RoleEnum {

    TOP_ADMIN(2, "最高级管理员"),
    ADMIN(1, "管理员"),
    CUSTOMER(0, "普通用户"),
    ;

    Integer code;
    String desc;

    RoleEnum(Integer code) {
        this.code = code;
    }

    RoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }}
