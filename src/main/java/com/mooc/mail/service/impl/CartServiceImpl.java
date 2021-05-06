package com.mooc.mail.service.impl;

import com.google.gson.Gson;
import com.mooc.mail.bean.Cart;
import com.mooc.mail.bean.Product;
import com.mooc.mail.dao.ProductMapper;
import com.mooc.mail.enumUtils.ProductEnum;
import com.mooc.mail.enumUtils.ResponseEnum;
import com.mooc.mail.form.CartsAddForm;
import com.mooc.mail.form.CartsUpdateForm;
import com.mooc.mail.service.CartService;
import com.mooc.mail.vo.CartProductVo;
import com.mooc.mail.vo.CatsVo;
import com.mooc.mail.vo.ResponseVo;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private static final String CART_RADIS_KEY = "cart_%d";
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Gson gson = new Gson();;

    @Override
    public ResponseVo add(CartsAddForm cartsAddForm, Integer uid) {

        Product product = productMapper.selectById(cartsAddForm.getProductId());
        //判断商品是否存在
        if (product == null) {
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXEIT);
        }
        //判断商品的状态是否正常
        if (!(product.getStatus().equals(ProductEnum.ON_SALE.getCode()))) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        //判断商品的库存是否正常
        if (product.getStock() <= 0) {
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        String radisKey = String.format(CART_RADIS_KEY, uid);
        Cart cart ;
        HashOperations<String, String, String> stringStringObjectHashOperations = redisTemplate.opsForHash();
        String value = stringStringObjectHashOperations.get(radisKey, product.getId().toString());
        if (StringUtil.isNullOrEmpty(value)) {
            cart = new Cart(product.getId(), 1, cartsAddForm.getSelected());
        } else {
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setProductSelected(cartsAddForm.getSelected());
        }
        stringStringObjectHashOperations.put(radisKey, cart.getProductId().toString(), gson.toJson(cart));
        ResponseVo<CatsVo> list = list(uid);
        return list;
    }

    @Override
    public ResponseVo<CatsVo> list(Integer uid) {
        CatsVo catsVo = new CatsVo();
        Boolean selectedAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        //获得redis里面HashMap的key
        String redisKey = String.format(CART_RADIS_KEY, uid);
        //根据key获得相应的value
        HashOperations<String, String, String> redisHash = redisTemplate.opsForHash();
        //获取HashMap里面所有的键值对
        Map<String, String> entries = redisHash.entries(redisKey);
        //从entries里面得到所有的key值，并查询数据库得到Product的集合
        Set<String> strings = entries.keySet();
        Collection<String> values = entries.values();
        List<Cart> carts = new ArrayList<>();
        values.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Cart cart = gson.fromJson(s, Cart.class);
                carts.add(cart);
            }
        });
        List<Integer> ids = new ArrayList<>();
        for (String string : strings) {
            ids.add(Integer.valueOf(string));
        }
        List<Product> productsByIdList = productMapper.getProductsByIdList(ids);
        //log.info("productsByIdList" + productsByIdList);
        //根据获得的products集合，转换成CartProductVo
        List<CartProductVo> cartProductVos = new ArrayList<>();
        for (Product product : productsByIdList) {
            for (Cart cart : carts) {
                if (cart.getProductId() == product.getId()) {
                    CartProductVo cartProductVo = new CartProductVo(cart.getProductId(), cart.getQuantity(), product.getName(), product.getSubtitle(), product.getMainImage(), product.getPrice(), product.getStatus(), product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())), product.getStock(), cart.getProductSelected());
                    //catsVo.setCartTotalPrice(catsVo.getCartTotalPrice().add(cartProductVo.getProductTotalPrice()));
                    if (cart.getProductSelected()) {
                        cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
                    }
                    cartTotalQuantity = cart.getQuantity() + cartTotalQuantity;
                    if (!cart.getProductSelected()) {
                        selectedAll = false;
                    }
                    cartProductVos.add(cartProductVo);
                }
            }
        }
        catsVo.setCartTotalPrice(cartTotalPrice);
        catsVo.setSelectedAll(selectedAll);
        catsVo.setCartTotalQuantity(cartTotalQuantity);
        catsVo.setCartProductVoList(cartProductVos);
        return ResponseVo.success(catsVo);
    }

    @Override
    public ResponseVo update(Integer uid, Integer productId, CartsUpdateForm cartsUpdateForm) {
        //根据uid和productId查出购物车中的产品
        String s = String.format(CART_RADIS_KEY, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String value = opsForHash.get(s, String.valueOf(productId));
        if (StringUtil.isNullOrEmpty(value)) {
            return  ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXEIT);
        }
        Cart cart = gson.fromJson(value, Cart.class);
        //然后判断两个参数的值是否为null
        if (cartsUpdateForm != null) {
            if (cartsUpdateForm.getQuantity() != null && cartsUpdateForm.getQuantity() >= 0) {
                cart.setQuantity(cartsUpdateForm.getQuantity());
            } else if (cartsUpdateForm.getSelected()) {
                cart.setProductSelected(cartsUpdateForm.getSelected());
            }
        }
        opsForHash.put(s, String.valueOf(productId), gson.toJson(cart));
        return list(uid);
    }

    @Override
    public ResponseVo delete(Integer uid, Integer productId) {
        String s = String.format(CART_RADIS_KEY, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String value = opsForHash.get(s, String.valueOf(productId));
        if (StringUtil.isNullOrEmpty(value)) {
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXEIT);
        }
        opsForHash.delete(s, String.valueOf(productId));
        return list(uid);
    }

    @Override
    public ResponseVo selectAll(Integer uid) {
        List<Cart> carts = listForCart(uid);
        String s = String.format(CART_RADIS_KEY, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        carts.forEach(new Consumer<Cart>() {
            @Override
            public void accept(Cart cart) {
                cart.setProductSelected(true);
                opsForHash.put(s, String.valueOf(cart.getProductId()), gson.toJson(cart));
            }
        });

        return list(uid);
    }

    @Override
    public ResponseVo unSelectAll(Integer uid) {
        List<Cart> carts = listForCart(uid);
        String s = String.format(CART_RADIS_KEY, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        carts.forEach(new Consumer<Cart>() {
            @Override
            public void accept(Cart cart) {
                cart.setProductSelected(false);
                opsForHash.put(s, String.valueOf(cart.getProductId()), gson.toJson(cart));
            }
        });
        return list(uid);
    }

    @Override
    public ResponseVo cartSum(Integer uid) {
        List<Cart> carts = listForCart(uid);
        Integer sum = 0;
        for (Cart cart : carts) {
            sum = sum + cart.getQuantity();
        }
        return ResponseVo.success(sum);
    }

    public List<Cart> listForCart(Integer uid) {
        String s = String.format(CART_RADIS_KEY, uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> entries = opsForHash.entries(s);
        Collection<String> values = entries.values();
        List<Cart> carts = new ArrayList<>();
        values.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                carts.add(gson.fromJson(s, Cart.class));

            }
        });
        return carts;
    }
}
