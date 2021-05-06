package com.mooc.mail.service;

import com.github.pagehelper.PageInfo;
import com.mooc.mail.form.ShippingAddForm;
import com.mooc.mail.vo.ResponseVo;

import javax.xml.ws.Response;
import java.util.Map;

public interface ShippingService {
    ResponseVo<Map<String,Integer>> add(ShippingAddForm shippingAddForm, Integer uid);
    ResponseVo delete(Integer uid, Integer Shipping);
    ResponseVo update(ShippingAddForm shippingAddForm, Integer uid, Integer shippingId);
    ResponseVo<PageInfo> list(Integer uid, Integer pageSize, Integer pageNum);
}
