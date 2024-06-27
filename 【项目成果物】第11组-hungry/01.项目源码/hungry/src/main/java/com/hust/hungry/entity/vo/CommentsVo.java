package com.hust.hungry.entity.vo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CommentsVo {

    @TableId(value = "commentId",type = IdType.AUTO)
    private Integer commentId;

    private String userName;
    private String comment;
    private String commentTime;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
