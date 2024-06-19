package com.hust.hungry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust.hungry.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}