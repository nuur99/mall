package com.mooc.mail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartProductVo {
            private Integer productId;
            private Integer quantity;
            private String productName;
            private String productSubtitle;
            private String productMainImage;
            private BigDecimal productPrice;
            private Integer productStatus;
            private BigDecimal productTotalPrice;
            private Integer productStock;
            private Boolean productSelected;
}
