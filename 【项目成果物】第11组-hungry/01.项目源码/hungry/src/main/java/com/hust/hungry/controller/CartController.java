package com.hust.hungry.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hust.hungry.entity.Cart;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.User;
import com.hust.hungry.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartMapper cartMapper;

    @PostMapping("/update")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        // 使用 MybatisPlus 的 LambdaQueryWrapper 来查找购物车记录
        LambdaQueryWrapper<Cart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Cart::getUserId, cart.getUserId())
                .eq(Cart::getFoodId, cart.getFoodId())
                .eq(Cart::getBusinessId, cart.getBusinessId());

        Cart existingCart = cartMapper.selectOne(lambdaQueryWrapper);

        if (existingCart != null) {
            // 如果存在,则将 quantity 加 1
            existingCart.setQuantity(cart.getQuantity());
            cartMapper.updateById(existingCart);
            return ResponseEntity.status(HttpStatus.OK).body(existingCart);
        } else {
            // 如果不存在,则插入新记录
            cartMapper.insert(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }
    }
    @GetMapping("/getInfo")
    public JsonResult getCartInfo(@RequestParam(value = "businessId") Integer businessId,
                                  @RequestParam(value = "userId") String userId){
        LambdaQueryWrapper<Cart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Cart::getUserId,userId)
                .eq(Cart::getBusinessId, businessId);
        List<Cart> cartList = cartMapper.selectList(lambdaQueryWrapper);
        return new JsonResult(cartList);
    }
}