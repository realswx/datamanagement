package com.dm.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginForm {

    //    @NotEmpty，用于集合
    @NotBlank //用于String，会判断空格
    private String username;

    @NotBlank
    private String password;

}
