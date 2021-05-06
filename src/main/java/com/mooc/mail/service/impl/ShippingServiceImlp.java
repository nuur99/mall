package com.mooc.mail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mooc.mail.bean.Shipping;
import com.mooc.mail.dao.ShippingMapper;
import com.mooc.mail.enumUtils.ResponseEnum;
import com.mooc.mail.form.ShippingAddForm;
import com.mooc.mail.service.ShippingService;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class ShippingServiceImlp implements ShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ResponseVo<Map<String, Integer>> add(ShippingAddForm shippingAddForm, Integer uid) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(shippingAddForm, shipping);
        shipping.setUserId(uid);
        shipping.setCreateTime(new Date());
        int insert = shippingMapper.insert(shipping);
        if (insert == 0) {
            return ResponseVo.error("新建地址失败");
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("shippingId", shipping.getId());
        return ResponseVo.success("新建地址成功", map);
    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        int deleteById = shippingMapper.deleteByUidAndShippingId(uid, shippingId);
        if (deleteById == 0) {
            return ResponseVo.error("删除地址失败");
        }
        return ResponseVo.success("删除地址成功");
    }

    @Override
    public ResponseVo update(ShippingAddForm shippingAddForm, Integer uid, Integer shippingId) {
        Shipping byUidAndShippingId = shippingMapper.getByUidAndShippingId(uid, shippingId);
        if (byUidAndShippingId == null) {
            return ResponseVo.error(ResponseEnum.SHIPPING_NOT_EXEIT);
        }
        BeanUtils.copyProperties(shippingAddForm, byUidAndShippingId);
        byUidAndShippingId.setUpdateTime(new Date());
        Integer update = shippingMapper.updateById(byUidAndShippingId);
        if (update == 0) {
            return ResponseVo.error("地址更新失败");
        }
        return ResponseVo.success("地址更新成功");
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippings = shippingMapper.getShippingsByUid(uid);
        List<Shipping> responseShippings = new ArrayList<>();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(shippings);
        return ResponseVo.success(pageInfo);
    }
}
