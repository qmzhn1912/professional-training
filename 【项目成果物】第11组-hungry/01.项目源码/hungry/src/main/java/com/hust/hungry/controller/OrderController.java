package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.*;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.mapper.*;
import com.hust.hungry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private DeliveryaddressMapper deliveryaddressMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderdetailetMapper orderdetailetMapper;


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

    @PostMapping("/produce")
    public void produceOrder(@RequestBody Orders order){
        Date date=new Date();
        String time = date.getTime()+"";
        double orderToal = 0;
        LambdaQueryWrapper<Cart> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //查询条件
        lambdaQueryWrapper.eq(Cart::getBusinessId,order.getBusinessId());
        lambdaQueryWrapper.eq(Cart::getUserId,order.getUserId());

        List<Cart> cartList = cartMapper.selectList(lambdaQueryWrapper);
        for(Cart cart:cartList){
            Integer foodId = cart.getFoodId();
            orderToal += cart.getQuantity()*foodMapper.selectById(foodId).getFoodPrice();
        }
//        System.out.println(orderToal);
        QueryWrapper<Deliveryaddress> queryWrapper=new QueryWrapper<>();
        //查询条件
        queryWrapper.eq("userId",order.getUserId());
        //查询列
        queryWrapper.select(true,"daId");
        Deliveryaddress deliveryaddress = deliveryaddressMapper.selectOne(queryWrapper);
//        System.out.println(deliveryaddress.getDaId());
        order.setOrderTotal(orderToal);
//        System.out.println(order.getOrderTotal());
        order.setDaId(deliveryaddress.getDaId());
        orderMapper.insert(order);
        for(Cart cart:cartList){
            Integer foodId = cart.getFoodId();
            Orderdetailet orderdetailet=new Orderdetailet();
            orderdetailet.setOrderId(order.getOrderId());
            orderdetailet.setFoodId(foodId);
            orderdetailet.setQuantity(cart.getQuantity());
            orderdetailetMapper.insert(orderdetailet);
        }
        LambdaQueryWrapper<Cart> lambdaQueryWrapper_sd = new LambdaQueryWrapper<>();
        //删除条件
        lambdaQueryWrapper_sd.eq(Cart::getUserId,order.getUserId());
        cartMapper.delete(lambdaQueryWrapper_sd);
    }



    @GetMapping("/user")
    public ResponseEntity<List<Orders>> getOrdersByUserId(@RequestParam("userId") String userId) {
        List<Orders> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/user_detail")
    public ResponseEntity<List<Orderdetailet>> getOrderdetailetByOrderId(@RequestParam("orderId") String orderId) {
        List<Orderdetailet> orderdetail = orderService.getOrderdetailetByOrderId( orderId);
        return new ResponseEntity<>(orderdetail, HttpStatus.OK);
    }
}

