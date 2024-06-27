package com.hust.hungry.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hust.hungry.entity.Comments;
import com.hust.hungry.entity.vo.CommentsVo;

public interface CommentsService extends IService<Comments> {
     public Page<CommentsVo> getCommentsByKeyPage(Page<CommentsVo> page,Integer businessId);
}
