package com.mooc.mail.service;

import com.mooc.mail.form.CartsAddForm;
import com.mooc.mail.form.CartsUpdateForm;
import com.mooc.mail.vo.CatsVo;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.stereotype.Service;


public interface CartService {

    ResponseVo add(CartsAddForm cartsAddForm, Integer uid);

    ResponseVo list(Integer uid);

    ResponseVo update(Integer uid, Integer productId, CartsUpdateForm cartsUpdateForm);
}
