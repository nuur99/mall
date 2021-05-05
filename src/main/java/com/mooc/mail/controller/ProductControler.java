package com.mooc.mail.controller;

import com.github.pagehelper.PageInfo;
import com.mooc.mail.service.ProductService;
import com.mooc.mail.vo.ProductVo;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductControler {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseVo<PageInfo> getProducts(@RequestParam(required = false) Integer categoryId, @RequestParam(required = false ,defaultValue = "10") Integer pageSize, @RequestParam(required = false ,defaultValue = "1")Integer pageNum) {
        PageInfo pageInfo = productService.getProductVoListByCategoryList(categoryId, pageSize, pageNum);
        return ResponseVo.success(pageInfo);
    }
}
