package com.mooc.mail.service;

import com.github.pagehelper.PageInfo;
import com.mooc.mail.form.ShippingAddForm;
import com.mooc.mail.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ShippingServiceImlpTest {

    @Autowired
    ShippingService shippingService;
    @Test
    void add() {
        ShippingAddForm shippingAddForm = new ShippingAddForm("燎师兄", "010", "18688888888", "北京", "北京市", "海淀区", "中关村", "10000");
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(shippingAddForm, 1);
        log.info("responseVo--->add" + responseVo);
    }
    @Test
    void delete() {
        ResponseVo delete = shippingService.delete(1, 7);
        log.info("delete ->>>>" + delete);
    }
    @Test
    void update() {
        ShippingAddForm shippingAddForm = new ShippingAddForm("燎师兄2", "010",
                "18688888888", "北京", "北京市", "海淀区",
                "中关村", "10000");
        ResponseVo responseVo = shippingService.update(shippingAddForm, 1, 5);
        log.info("update->>>>>>" + responseVo);
    }

    @Test
    void list() {
        ResponseVo<PageInfo> list = shippingService.list(1, 5, 1);
        log.info("list->>>>>>>>>>" + list);
    }
}