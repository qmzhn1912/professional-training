package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("orderdetailet")
public class Orderdetailet {

    private Integer orderId;
    private  Integer foodId;

    @TableId(value = "odId",type = IdType.AUTO)
    private  Integer odId;

    private  Integer quantity;
    public Orderdetailet() {
        this.orderId = 0;
    }
}