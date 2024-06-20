package com.hust.hungry.mapper;

import com.hust.hungry.HungryApplication;
import com.hust.hungry.entity.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

     @Test
    void testInsert() {
        User user = new User("44444444444","john","123456",1,"elwsifbwvbdivu",1);
        System.out.println(user.toString());
        int result = userMapper.insert(user);
        Assertions.assertEquals(1, result);
    }

      @Test
    void testQuery() {

        List<User> users= userMapper.selectList(null);
          for (int i = 0; i < users.size(); i++) {
              System.out.printf(users.get(i).toString());
          }
    }
}