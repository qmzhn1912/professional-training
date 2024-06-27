package com.hust.hungry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.Comments;
import com.hust.hungry.entity.vo.CommentsVo;
import com.hust.hungry.entity.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {
    @Select("""
        <script>
        select c.commentId,u.userName,c.comment,c.commentTime
          from comments c
    	left join user u on c.userId=u.userId
            <where>
                    c.businessId = #{businessId}
            </where>
        </script>
    """)
    public Page<CommentsVo> selectByKeyPage(Page<CommentsVo> page, @Param("businessId") Integer businessId);
}
