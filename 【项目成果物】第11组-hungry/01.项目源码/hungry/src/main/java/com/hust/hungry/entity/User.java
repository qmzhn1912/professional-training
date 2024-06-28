package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User{
    @TableId(value = "userId")
    private String userId;

    @TableField(select = false)
    private String password;

    private String userName;

    private Integer userSex;

    private String userImg;

    private Integer delTag;
    // type 1代表用户 0代表商家
    private Integer type;

    public User(){

    }
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userImg='" + userImg + '\'' +
                ", delTag=" + delTag + '\'' +
                ", type=" + type +
                '}';
    }

    // Getters and Setters
}