package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * restful风格
     * url
     * http://localhost:8001/order/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public JsonResult getOrderById(@PathVariable("id") String id){
        //1、取  参数列表
        //2、调
        OrderVo orderVo= orderService.getOrderById(id);
        if(orderVo!=null){
            return new JsonResult(orderVo);
        }else{
            return new JsonResult(false,"该编号的订单不存在！");
        }

    }

    /**
     * url
     * http://localhost:8001/order/get?oid=1
     * @param id
     * @return
     */
    @GetMapping("/get")
    public JsonResult getOrderById2(@RequestParam("oid") String id){
        OrderVo orderVo= orderService.getOrderById(id);
        if(orderVo!=null){
            return new JsonResult(orderVo);
        }else{
            return new JsonResult(false,"该编号的订单不存在！");
        }

    }

    /**
     * url
     * http://localhost:8001/order/search?key=11
     * http://localhost:8001/order/search
     * null
     * @param key
     * @return
     */
    @GetMapping("/search")
    public JsonResult getOrderByKey(@RequestParam(value = "key",required = false) String key){
        List<OrderVo> orderVos= orderService.getOrderByKey(key);

        if(orderVos.size()>0){
            return new JsonResult(orderVos);
        }else{
            return new JsonResult(false,"没查到对应的数据！");
        }
    }


    /**
     * http://localhost:8001/order/page
     * @param pn
     * @param ps
     * @param key
     * @return
     */
    @GetMapping("/page")
    public JsonResult getOrderByPage(@RequestParam(value = "pn",defaultValue ="1" ,required = false) Integer pn,
                                     @RequestParam(value = "ps",defaultValue = "3",required = false) Integer ps,
                                     @RequestParam(value = "key",required = false) String key){
        //1
        //2
        Page<OrderVo> page=new Page<>(pn,ps);
        List<OrderItem> orderItems=new ArrayList<>();
        OrderItem oitem=new OrderItem();
        oitem.setColumn("o.orderId");
        oitem.setAsc(false);
        orderItems.add(oitem);
        page.setOrders(orderItems);
        page=orderService.getOrderByKeyPage(page,key);
        //3
        return  new JsonResult(page);
    }

}

