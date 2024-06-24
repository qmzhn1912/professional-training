package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.Cart;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}