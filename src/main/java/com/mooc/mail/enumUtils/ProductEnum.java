package com.mooc.mail.enumUtils;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ProductEnum {
    ON_SALE(1),
    OFF_SALE(2),
    DELETE(3);
    Integer code;
    ProductEnum (Integer code) {
        this.code = code;
    }

}
