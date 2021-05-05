package com.mooc.mail.service;

import com.mooc.mail.bean.Category;
import com.mooc.mail.vo.CategoryVo;

import java.util.List;

public interface CategoryService {
    List<CategoryVo> getAllCategory();

    List<Integer> findSubCategory(Integer categoryId);
}
