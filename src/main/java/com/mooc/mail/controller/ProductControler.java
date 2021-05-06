package com.mooc.mail.controller;

import com.github.pagehelper.PageInfo;
import com.mooc.mail.bean.Product;
import com.mooc.mail.enumUtils.ProductEnum;
import com.mooc.mail.enumUtils.ResponseEnum;
import com.mooc.mail.service.ProductService;
import com.mooc.mail.vo.ProductDetailVo;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControler {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseVo<PageInfo> getProducts(@RequestParam(required = false) Integer categoryId, @RequestParam(required = false ,defaultValue = "10") Integer pageSize, @RequestParam(required = false ,defaultValue = "1")Integer pageNum) {
        PageInfo pageInfo = productService.getProductVoListByCategoryList(categoryId, pageSize, pageNum);
        return ResponseVo.success(pageInfo);
    }

    @GetMapping("/products/{productId}")
    public ResponseVo getProductById(@PathVariable Integer productId) {
        Product product = productService.getById(productId);
        if (product == null || product.getStatus().equals(ProductEnum.OFF_SALE.getCode()) || product.getStatus().equals(ProductEnum.DELETE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);
        return ResponseVo.success(productDetailVo);
    }
}
