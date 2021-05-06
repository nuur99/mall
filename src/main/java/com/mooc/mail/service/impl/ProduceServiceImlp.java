package com.mooc.mail.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mooc.mail.bean.Product;
import com.mooc.mail.dao.ProductMapper;
import com.mooc.mail.service.CategoryService;
import com.mooc.mail.service.ProductService;
import com.mooc.mail.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class ProduceServiceImlp extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryService categoryService;
    @Override
    public List<ProductVo> getProductVoListByCategoryList(Integer categoryId) {
        List<Integer> subCategory = categoryService.findSubCategory(categoryId);
        if (categoryId != null) {
            subCategory.add(categoryId);
        }
        PageHelper.startPage(1, 1);
        List<Product> products = productMapper.getProductsByCategoryIdList(subCategory);
        List<ProductVo> productVoList = new ArrayList<>();
        for (Product product : products) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(product, productVo);
            productVoList.add(productVo);
        }
        log.info(products.toString());
        return productVoList;
    }

    public PageInfo getProductVoListByCategoryList(Integer categoryId, Integer pageSize, Integer pageNum) {
        List<Integer> subCategory = categoryService.findSubCategory(categoryId);
        if (categoryId != null) {
            subCategory.add(categoryId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.getProductsByCategoryIdList(subCategory);
        List<ProductVo> productVoList = new ArrayList<>();
        for (Product product : products) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(product, productVo);
            productVoList.add(productVo);
        }
        PageInfo pageInfo = new PageInfo(products);
        pageInfo.setList(productVoList);
        log.info(products.toString());
        return pageInfo;
    }
}
