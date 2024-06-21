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

    public User(){

    }

    public User(String id, String name,String password,Integer userSex,String userImg,Integer delTag) {
        this.userId = id;
        this.userName = name;
        this.password = password;
        this.userSex = userSex;
        this.userImg = userImg;
        this.delTag = delTag;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userImg='" + userImg + '\'' +
                ", delTag=" + delTag +
                '}';
    }

    // Getters and Setters
}