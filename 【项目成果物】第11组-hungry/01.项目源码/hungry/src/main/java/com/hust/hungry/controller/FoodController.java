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
    @GetMapping("/getInfo")
    public JsonResult getFoodInfo(@RequestParam("foodId")Integer foodId) {
        LambdaQueryWrapper<Food> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //查询条件
        lambdaQueryWrapper.eq(Food::getFoodId,foodId);
        Food food = foodMapper.selectOne(lambdaQueryWrapper);
        return new JsonResult(food);
    }
    @PostMapping("/add/{businessId}")
    public JsonResult addBusinessFoodList(@PathVariable("businessId")Integer businessId,@RequestBody Food food) {
        food.setBusinessId(businessId);
        foodMapper.insert(food);
        return new JsonResult("添加成功");
    }
    @DeleteMapping("/del")
    public JsonResult delBusinessFoodList(@RequestParam("foodId")Integer foodId) {
        LambdaQueryWrapper<Food> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Food::getFoodId,foodId);
        foodMapper.delete(lambdaQueryWrapper);
        return new JsonResult("删除成功");
    }
    @PutMapping("/update")
    public JsonResult updateBusinessFoodList(@RequestBody Food food){
        Food updatedFood = foodMapper.selectById(food.getFoodId());
        updatedFood.setFoodName(food.getFoodName());
        updatedFood.setFoodExplain(food.getFoodExplain());
        updatedFood.setFoodPrice(food.getFoodPrice());
        updatedFood.setRemarks(food.getRemarks());
        updatedFood.setFoodImg(food.getFoodImg());
        foodMapper.updateById(updatedFood);
        return new JsonResult(updatedFood);
    }
}
