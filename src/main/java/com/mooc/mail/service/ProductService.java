package com.mooc.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.mooc.mail.bean.Product;
import com.mooc.mail.vo.ProductVo;

import java.util.List;

public interface ProductService extends IService<Product> {
    List<ProductVo> getProductVoListByCategoryList(Integer categoryId);
    PageInfo getProductVoListByCategoryList(Integer categoryId, Integer pageSize, Integer pageNum);
}
