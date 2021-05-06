package com.mooc.mail.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mooc.mail.enumUtils.ResponseEnum;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {

    public static volatile ResponseVo responseVo = null;

    private Integer state;
    private String msg;
    private T data;

    public ResponseVo(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public ResponseVo(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public ResponseVo(Integer state, String msg, T data) {
        this.msg = msg;
        this.state = state;
        this.data = data;
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }
    public static <T> ResponseVo<T> successByMsg(String msg) {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(), msg);
    }
    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(), data);
    }

    public static <T> ResponseVo<T> success(String msg, T data) {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(), msg, data);
    }
    public static <T> ResponseVo<T> userExeit() {
        return new ResponseVo(ResponseEnum.USER_EXIST.getCode(), ResponseEnum.USER_EXIST.getDesc());
    }
    public static <T> ResponseVo<T> paramterError(String msg) {
        return new ResponseVo(ResponseEnum.PARAMTER_ERROR.getCode(), msg);
    }

    public static <T> ResponseVo<T> dbRegisterError() {
        return new ResponseVo(ResponseEnum.DB_REGISTER_ERROR.getCode(), ResponseEnum.DB_REGISTER_ERROR.getDesc());
    }
    public static <T> ResponseVo<T> error(ResponseEnum paramterError, String s) {
        return new ResponseVo(ResponseEnum.ERROR.getCode(), s);
    }
    public static <T> ResponseVo<T> error(String msg) {
        return new ResponseVo(ResponseEnum.ERROR.getCode(), msg);
    }
    public static <T> ResponseVo<T> error( ResponseEnum responseEnum) {
        return new ResponseVo(responseEnum.getCode(), responseEnum.getDesc());
    }



    public ResponseVo () {

    }

    private static ResponseVo getResponseVoInstance() {
        if (responseVo == null) {
            synchronized (ResponseVo.class) {
                if (responseVo == null) {
                    responseVo = new ResponseVo();
                }
            }
        }

        return responseVo;
    }

}
