package com.dm.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserUpdateForm {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotNull
    private Date birthday;

}
