package com.mooc.mail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mooc.mail.bean.Shipping;
import com.mooc.mail.form.ShippingAddForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShippingMapper extends BaseMapper<Shipping> {
    Integer deleteByUidAndShippingId(Integer uid, Integer shippingId);
    Shipping getByUidAndShippingId(Integer uid, Integer shippingId);
    List<Shipping> getShippingsByUid(Integer uid);
}
