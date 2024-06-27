package com.hust.hungry.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.hungry.entity.Orderdetailet;
import com.hust.hungry.entity.Orders;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.mapper.OrderMapper;
import com.hust.hungry.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private  OrderMapper orderMapper;
    @Override
    public OrderVo getOrderById(String id) {
       // return orderMapper.selectById(id);
        return  baseMapper.selectById(id);
    }

    @Override
    public List<OrderVo> getOrderByKey(String key) {
            //System.out.println(key);
        //return orderMapper.selectByKey(key);
        return baseMapper.selectByKey(key);
    }

    @Override
    public Page<OrderVo> getOrderByKeyPage(Page<OrderVo> page, String key) {
        return  baseMapper.selectByKeyPage(page,key);
    }


    @Override
    public  List<Orders> getOrdersByUserId(String userId) {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    public List<Orderdetailet> getOrderdetailetByOrderId(String orderId){
        return orderMapper.getOrderdetailetByOrderId(orderId);
    };
}
