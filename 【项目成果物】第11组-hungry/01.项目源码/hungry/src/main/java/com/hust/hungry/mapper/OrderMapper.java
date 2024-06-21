package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust.hungry.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.User;
import org.springframework.core.annotation.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}