package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.User;

@Mapper
public interface OrderdetailetMapper extends BaseMapper<User> {
}