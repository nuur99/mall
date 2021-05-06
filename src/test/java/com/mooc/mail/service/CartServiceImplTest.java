package com.mooc.mail.service;

import com.mooc.mail.form.CartsAddForm;
import com.mooc.mail.form.CartsUpdateForm;
import com.mooc.mail.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CartServiceImplTest {

    @Autowired
    private CartService cartService;
    @Test
    void add() {
        CartsAddForm cartsAddForm = new CartsAddForm();
        cartsAddForm.setProductId(28);
        cartService.add(cartsAddForm, 1);
    }
    @Test
    void list() {
        cartService.list(1);
    }
    @Test
    void update() {
        ResponseVo update = cartService.update(1, 28, new CartsUpdateForm(5, false));
        log.info("update" + update);
    }
}