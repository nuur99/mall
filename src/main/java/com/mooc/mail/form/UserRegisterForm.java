package com.mooc.mail.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class UserRegisterForm {


    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
