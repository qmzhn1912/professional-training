package com.hust.hungry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.hungry.entity.User;
import com.hust.hungry.mapper.UserMapper;
import com.hust.hungry.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public int register(User user) {
        //处理数据的过程
        //return  userMapper.insert(user);
        return baseMapper.insert(user);
    }
}