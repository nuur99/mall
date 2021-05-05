package com.mooc.mail.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ProduceServiceImlpTest {

    @Autowired
    ProductService productService;
    @Test
    void getProductVoListByCategoryList() {
        productService.getProductVoListByCategoryList(null, 2, 2);
    }
}