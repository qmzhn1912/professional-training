package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust.hungry.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.User;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}