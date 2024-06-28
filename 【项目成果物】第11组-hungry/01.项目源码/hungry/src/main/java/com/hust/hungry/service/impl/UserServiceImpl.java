package com.hust.hungry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.hungry.entity.*;
import com.hust.hungry.mapper.UserMapper;
import com.hust.hungry.mapper.CartMapper;
import com.hust.hungry.mapper.DeliveryaddressMapper;
import com.hust.hungry.mapper.OrderMapper;
import com.hust.hungry.mapper.OrderdetailetMapper;
import com.hust.hungry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private DeliveryaddressMapper deliveryAddressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderdetailetMapper orderDetailMapper;
    @Override
    public int register(User user) {
        //处理数据的过程
        //return  userMapper.insert(user);
        return baseMapper.insert(user);
    }

    @Override
    public void deleteUser(String userId) {
        // 删除用户信息
        userMapper.deleteById(userId);

        // 删除用户购物车信息
        cartMapper.delete(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId));

        // 删除用户收货地址信息
        deliveryAddressMapper.delete(new LambdaQueryWrapper<Deliveryaddress>().eq(Deliveryaddress::getUserId, userId));

        // 删除用户订单信息
        List<Orders> orders = orderMapper.selectList(new LambdaQueryWrapper<Orders>().eq(Orders::getUserId, userId));
        for (Orders order : orders) {
            // 删除订单详情信息
            orderDetailMapper.delete(new LambdaQueryWrapper<Orderdetailet>().eq(Orderdetailet::getOrderId, order.getOrderId()));
        }
        orderMapper.delete(new LambdaQueryWrapper<Orders>().eq(Orders::getUserId, userId));
    }
    @Override
    public User saveUser(User user) {
        // 设置一些默认值
        // 保存商家信息到数据库
        userMapper.insert(user);
        return user;
    }
}