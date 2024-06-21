package com.hust.hungry.entity.vo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class OrderVo {

    @TableId(value = "orderId",type = IdType.AUTO)
    private Integer orderId;
    private  String userId;
    private String userName;
    private Integer businessId;
    private String businessName;
    private  String orderDate;
    private Double orderTotal;
    private  Integer daId;
    private Integer orderState;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
