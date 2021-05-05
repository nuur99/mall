package com.mooc.mail.controller;

import com.mooc.mail.bean.Category;
import com.mooc.mail.service.CategoryService;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryControler {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    public ResponseVo getAllCategory() {
        return ResponseVo.success(categoryService.getAllCategory());
    }
}
