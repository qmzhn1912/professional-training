package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("cart")
public class Cart {
    @TableId(value = "cartId",type = IdType.AUTO)
    private Integer cartId;
    private  String userId;
    private Integer businessId;
    private  Integer foodId;
    private Integer quantity=1;
}