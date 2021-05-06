package com.mooc.mail.controller;

import com.github.pagehelper.PageInfo;
import com.mooc.mail.bean.User;
import com.mooc.mail.consts.MallConst;
import com.mooc.mail.form.ShippingAddForm;
import com.mooc.mail.service.ShippingService;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.Map;

@RestController
public class ShippingControler {
    @Autowired
    private ShippingService shippingService;
    @GetMapping("/shippings")
    public ResponseVo<PageInfo> list(HttpSession httpSession, @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        User user = (User) httpSession.getAttribute(MallConst.CURRENT_USER);
        return shippingService.list(user.getId(), pageSize, pageNum);
    }

    @PostMapping("/shippings")
    public ResponseVo add(HttpSession session, @RequestBody ShippingAddForm shippingAddForm) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        ResponseVo<Map<String, Integer>> add = shippingService.add(shippingAddForm, user.getId());
        return add;
    }

    @DeleteMapping("/shippings/{shippingId}")
    public ResponseVo delete(HttpSession session, @PathVariable Integer shippingId) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        ResponseVo responseVo = shippingService.delete(user.getId(), shippingId);
        return responseVo;
    }

    @PutMapping("/shippings/{shippingId}")
    public ResponseVo update(HttpSession session, @PathVariable Integer shippingId, @RequestBody ShippingAddForm shippingAddForm) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        ResponseVo responseVo = shippingService.update(shippingAddForm, user.getId(), shippingId);
        return responseVo;
    }


}
