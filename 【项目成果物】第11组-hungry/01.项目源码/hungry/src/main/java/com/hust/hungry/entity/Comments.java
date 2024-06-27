package com.hust.hungry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comments")
public class Comments {

    @TableId(value = "commentId",type = IdType.AUTO)
    private Integer commentId;

    private Integer businessId;
    private String userId;
    private String comment;
    private String commentTime;
}

