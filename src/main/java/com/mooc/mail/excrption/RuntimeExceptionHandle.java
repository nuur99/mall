package com.mooc.mail.excrption;

import com.mooc.mail.bean.User;
import com.mooc.mail.enumUtils.ResponseEnum;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RuntimeExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    public ResponseVo handle(RuntimeException e) {
        return ResponseVo.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public ResponseVo handleArithmeticException(RuntimeException e) {
        return ResponseVo.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserLoginException.class)
    public ResponseVo handleUserLoginException() {
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ResponseVo.error(ResponseEnum.PARAMTER_ERROR,bindingResult.getFieldError().getField() + bindingResult.getFieldError().getDefaultMessage());
    }
}
