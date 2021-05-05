package com.mooc.mail.service;

import com.mooc.mail.dao.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;
@Slf4j
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void testFindSubCategory() {
        List<Integer> subCategory = categoryService.findSubCategory(100001);
        log.info(subCategory.toString());

    }
}