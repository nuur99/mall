package com.mooc.mail.service;

import com.github.pagehelper.PageInfo;
import com.mooc.mail.vo.ProductVo;

import java.util.List;

public interface ProductService {
    List<ProductVo> getProductVoListByCategoryList(Integer categoryId);
    PageInfo getProductVoListByCategoryList(Integer categoryId, Integer pageSize, Integer pageNum);
}
