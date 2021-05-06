package com.mooc.mail.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CatsVo {
    private List<CartProductVo> cartProductVoList;
    private Boolean selectedAll;
    private BigDecimal cartTotalPrice;
    private Integer cartTotalQuantity;

}
