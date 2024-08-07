package com.hust.hungry.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("business")
public class Business {
    @TableId(value = "businessId",type = IdType.AUTO)
    private Integer businessId;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    private String businessImg;
    private Integer orderTypeId;
    private Float startPrice;
    private Float deliveryPrice;
    private  String remarks;
    private Integer appraiseNum;
    private Float score;
    private String userId;
    private Integer state;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
