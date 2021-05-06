package com.mooc.mail.controller;

import com.mooc.mail.form.CartsAddForm;
import com.mooc.mail.form.CartsUpdateForm;
import com.mooc.mail.service.CartService;
import com.mooc.mail.vo.CartProductVo;
import com.mooc.mail.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@RestController
public class CartsControler {

    @Autowired
    private CartService cartService;
    @PostMapping("/carts")
    public ResponseVo<CartProductVo> carstAdd(@Valid @RequestBody CartsAddForm cartsAddForm) {
        ResponseVo responseVo = cartService.add(cartsAddForm, 1);
        return responseVo;
    }

    @GetMapping("/carts")
    public ResponseVo cartsList() {
        return cartService.list(1);
    }

    @PostMapping("/carts/{productId}")
    public ResponseVo cartsUpdate(@PathVariable Integer productId, @RequestBody(required = false) CartsUpdateForm cartsUpdateForm) {
        return cartService.update(1, productId, cartsUpdateForm);
    }
}
