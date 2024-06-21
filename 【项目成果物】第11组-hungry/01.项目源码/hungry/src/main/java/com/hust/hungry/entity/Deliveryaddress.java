package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("deliveryaddress")
public class Deliveryaddress {
    @TableId(value = "daId",type = IdType.AUTO)
    private Integer daId;
    private  String userId;
    private  String address;
    private  String contactTel;
    private  String contactName;
    private  Integer contactSex;
}

