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
        NEED_LOGIN(10, "请先登录"),
        PRODUCT_NOT_EXEIT(11, "产品不存在"),
        CART_PRODUCT_NOT_EXEIT(14, "购物车产品不存在"),
        PRODUCT_STOCK_ERROR(13, "库存不足"),
        SHIPPING_NOT_EXEIT(15, "地址不存在"),
        PRODUCT_OFF_SALE_OR_DELETE(12, "商品下架或者删除");
        Integer code;
        String desc;
        ResponseEnum (Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
}
