package com.mooc.mail.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartsUpdateForm {
    private Integer quantity; //非必填
    private Boolean selected; //非必填
}
