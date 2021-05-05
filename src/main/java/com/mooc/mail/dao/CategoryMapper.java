package com.mooc.mail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mooc.mail.bean.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> getAllCategory();
}
