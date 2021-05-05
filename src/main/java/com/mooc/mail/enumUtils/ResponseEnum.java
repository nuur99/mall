package com.mooc.mail.enumUtils;

import lombok.Getter;

@Getter
public enum ResponseEnum {
        SUCCESS(0,"成功"),
        ERROR(-1,"服务端错误"),
        PASSWORD_ERROR(1, "密码错误"),
        USER_EXIST(2, "用户已经存在"),
        PARAMTER_ERROR(3, "参数错误"),
        DB_REGISTER_ERROR(4, "数据库异常，注册失败"),
        USER_OR_PASSWORD_ERROR(5, "用户名或者密码错误"),
        NEED_LOGIN(10, "请先登录");
        Integer code;
        String desc;
        ResponseEnum (Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
}
