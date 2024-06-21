package com.hust.hungry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.hungry.entity.User;
import com.hust.hungry.mapper.UserMapper;
import com.hust.hungry.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class BusinessImpl extends ServiceImpl<UserMapper, User> implements OrderService {
}