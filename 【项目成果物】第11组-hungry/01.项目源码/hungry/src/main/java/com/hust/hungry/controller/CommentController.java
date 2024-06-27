package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.Comments;
import com.hust.hungry.entity.Food;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.vo.CommentsVo;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.mapper.CommentsMapper;
import com.hust.hungry.mapper.FoodMapper;
import com.hust.hungry.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private CommentsService commentsService;
    @GetMapping("/list/{businessId}")
    public JsonResult listPage(@RequestParam(value = "pn",defaultValue ="1" ,required = false) Integer pn,
                               @RequestParam(value = "ps",defaultValue = "3",required = false) Integer ps,
                               @PathVariable("businessId") Integer businessId){
          Page<CommentsVo> page=new Page<>(pn,ps);
//        List<OrderItem> orderItems=new ArrayList<>();
//        OrderItem oitem=new OrderItem();
//        oitem.setColumn("o.orderId");
//        oitem.setAsc(false);
//        orderItems.add(oitem);
//        page.setOrders(orderItems);
          page=commentsService.getCommentsByKeyPage(page,businessId);
        //3
        return  new JsonResult(page);
    }
    @PostMapping("/add")
    public JsonResult addComment(@RequestBody Comments comments) {
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        comments.setCommentTime(formatter.format(date));
        System.out.println(comments);
        int rs = commentsMapper.insert(comments);
        if(rs > 0) {
            return new JsonResult("评论成功");
        }
        else{
            return new JsonResult("评论失败");
        }
    }
}
