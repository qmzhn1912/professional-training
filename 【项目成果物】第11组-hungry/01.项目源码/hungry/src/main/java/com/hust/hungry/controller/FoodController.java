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
        List<Food> foodList = foodMapper.selectList(lambdaQueryWrapper);
        return new JsonResult(foodList);
    }
    @GetMapping("/add")
    public JsonResult addBusinessFoodList(@RequestBody Food food) {
        foodMapper.insert(food);
        return new JsonResult("添加成功");
    }
    @DeleteMapping("/del")
    public JsonResult delBusinessFoodList(@RequestParam("businessId")Integer businessId) {
        LambdaQueryWrapper<Food> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Food::getBusinessId,businessId);
        foodMapper.delete(lambdaQueryWrapper);
        return new JsonResult("删除成功");
    }
    @PutMapping("/update")
    public JsonResult updateBusinessFoodList(@RequestBody Food food){
        Food updatedFood = foodMapper.selectById(food.getFoodId());
        updatedFood.setFoodName(food.getFoodName());
        updatedFood.setFoodName(food.getFoodExplain());
        updatedFood.setFoodName(food.getFoodExplain());
        updatedFood.setFoodName(food.getRemarks());
        foodMapper.updateById(updatedFood);
        return new JsonResult(updatedFood);
    }
}
