package com.mooc.mail.enumUtils;

import lombok.Getter;

@Getter
public enum UserEnum {

    ADMIN(0),
    CUSTOMER(1);
    Integer code;
    UserEnum(Integer code) {
        this.code = code;
    }
}
