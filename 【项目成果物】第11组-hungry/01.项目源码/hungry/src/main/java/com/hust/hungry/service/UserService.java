package com.hust.hungry.service;

import com.hust.hungry.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    public int register(User user);
    public void deleteUser(String userId);
    public User saveUser(User user);
}