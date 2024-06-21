package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("food")
public class Food {
    @TableId(value = "foodId",type = IdType.AUTO)
    private  String foodExplain;
    private Integer businessId;
    private  Integer foodId;
    private  String foodName;
    private  Long foodPrice;
    private  String remarks;
    private String foodImg;
}