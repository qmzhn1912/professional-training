package com.hust.hungry.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.hungry.entity.Comments;
import com.hust.hungry.entity.User;
import com.hust.hungry.entity.vo.CommentsVo;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.mapper.CommentsMapper;
import com.hust.hungry.service.CommentsService;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper,Comments> implements CommentsService {
    @Override
    public Page<CommentsVo> getCommentsByKeyPage(Page<CommentsVo> page,Integer businessId) {
        return  baseMapper.selectByKeyPage(page,businessId);
    }

}