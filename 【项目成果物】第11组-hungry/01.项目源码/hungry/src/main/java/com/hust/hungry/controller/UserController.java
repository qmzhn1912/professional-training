package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.User;
import com.hust.hungry.service.UserService;
import com.hust.hungry.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public JsonResult reg(@RequestBody User user){
        //1、取  取参数封装  看方法的参数
        // 2、调 业务逻辑层
        int rs= userService.register(user);
        // 3、转  输出结果
        //return
        if(rs==1){
            System.out.println("注册成功");
            return new JsonResult(true,"注册成功");
        }else{
            return new JsonResult(false,"注册失败");
        }
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody User user){
        //1
        //2
        System.out.println(user);
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,user.getUserName())
                .eq(User::getPassword,user.getPassword())
                .eq(User::getDelTag,1);
        User u= userService.getOne(lambdaQueryWrapper);
        if(u!=null){
            //生成令牌token字符串
            String token= JwtUtils.sign(u);
            return new JsonResult(true,u,token);
        }else {
            return new JsonResult(false,"用户名或密码错误！");
        }
    }

}