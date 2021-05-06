package com.mooc.mail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mooc.mail.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> getProductsByCategoryIdList(@Param("categoryIdList") List<Integer> categoryIdList);

    List<Product> getProductsByIdList(@Param("IdList") List<Integer> IdList);
}
