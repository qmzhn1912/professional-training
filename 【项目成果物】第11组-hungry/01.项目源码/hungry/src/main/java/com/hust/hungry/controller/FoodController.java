package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hust.hungry.entity.Food;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodMapper foodMapper;
    @GetMapping("/{businessId}")
    public JsonResult getFoodlist(@PathVariable("businessId")Integer businessId) {
        LambdaQueryWrapper<Food> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //查询条件
        lambdaQueryWrapper.eq(Food::getBusinessId,businessId);
        List<Food> businessList = foodMapper.selectList(lambdaQueryWrapper);
        return new JsonResult(businessList.size());
    }
}
