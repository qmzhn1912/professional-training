package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("orderdetailet")
public class Orderdetailet {
    @TableId(value = "foodId",type = IdType.AUTO)
    private Integer orderId;
    private  Integer foodId;
    private  Integer odId;
    private  Integer quantity;
}