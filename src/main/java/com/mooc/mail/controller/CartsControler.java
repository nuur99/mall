package com.mooc.mail.controller;

import com.mooc.mail.bean.User;
import com.mooc.mail.consts.MallConst;
import com.mooc.mail.form.CartsAddForm;
import com.mooc.mail.form.CartsUpdateForm;
import com.mooc.mail.service.CartService;
import com.mooc.mail.vo.CartProductVo;
import com.mooc.mail.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@RestController
public class CartsControler {

    @Autowired
    private CartService cartService;
    @PostMapping("/carts")
    public ResponseVo<CartProductVo> carstAdd(@Valid @RequestBody CartsAddForm cartsAddForm, HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        ResponseVo responseVo = cartService.add(cartsAddForm, user.getId());
        return responseVo;
    }

    @GetMapping("/carts")
    public ResponseVo cartsList(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.list(user.getId());
    }

    @PostMapping("/carts/{productId}")
    public ResponseVo cartsUpdate(HttpSession session, @PathVariable Integer productId, @RequestBody(required = false) CartsUpdateForm cartsUpdateForm) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.update(user.getId(), productId, cartsUpdateForm);
    }

    @PutMapping("/carts/selectAll")
    public ResponseVo cartsSelectAll(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.selectAll(user.getId());
    }
    @PutMapping("/carts/unselectAll")
    public ResponseVo cartsUnSelectAll(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.unSelectAll(user.getId());
    }
    @GetMapping("/carts/products/sum")
    public ResponseVo cartsProductsSum(HttpSession session) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.cartSum(user.getId());
    }

    @DeleteMapping("/carts/{productId}")
    public ResponseVo cartsDelete(HttpSession session, @PathVariable Integer productId) {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.delete(user.getId(), productId);
    }
}
