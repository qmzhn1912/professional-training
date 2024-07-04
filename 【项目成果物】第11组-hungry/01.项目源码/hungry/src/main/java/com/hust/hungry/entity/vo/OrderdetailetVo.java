package com.hust.hungry.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
public class OrderdetailetVo {

    private Integer orderId;
    private  Integer foodId;
    private String foodName;
    private Float foodPrice;
    @TableId(value = "odId",type = IdType.AUTO)
    private  Integer odId;
    private  Integer quantity;
}