package com.mooc.mail.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartsAddForm {
    @NotNull
    private Integer productId;
    private Boolean selected = true;
}
