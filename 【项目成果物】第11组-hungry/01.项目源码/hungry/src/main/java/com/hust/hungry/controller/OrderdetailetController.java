package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hust.hungry.entity.Cart;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.Orderdetailet;
import com.hust.hungry.entity.vo.OrderdetailetVo;
import com.hust.hungry.mapper.OrderdetailetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OrderdetailetController {
    @Autowired
    private OrderdetailetMapper orderdetailetMapper;
    @GetMapping("/get")
    public JsonResult getOrderdetailet(@RequestParam("orderId")Integer orderId) {
        List<OrderdetailetVo> orderdetailetList = orderdetailetMapper.selectOrderdetailetVoList(orderId);
        return new JsonResult(orderdetailetList);
    }
}
