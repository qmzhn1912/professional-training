package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("orders")
public class Orders {
    @TableId(value = "orderId",type = IdType.AUTO)
    private Integer orderId;
    private  String userId;
    private Integer businessId;
    private  String orderDate;
    private Double orderTotal;
    private  Integer daId;
    private Integer orderState;
}
