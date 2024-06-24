package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
        @Select("""
        select o.orderId,o.userId,u.userName,o.orderTotal,o.orderDate
          from orders o
    	left join user u on o.userId=u.userId
            where o.orderId=#{oid}
    """)
    public OrderVo selectById(@Param("oid") String id);

    // 动态的Sql
    // 根据参数是否有值，不为null拼接条件，为空不加
    @Select("""
        <script>
        select o.orderId,o.userId,u.userName,o.orderTotal,o.orderDate
          from orders o
    	left join user u on o.userId=u.userId
            <where>
               <if test="k!=null and k=''">
                    o.userId like concat('%',#{k},'%')
                    or
                    u.userName like concat('%',#{k},'%')
               </if>
            </where>
        </script>
    """)
    public List<OrderVo> selectByKey(@Param("k") String key);


    @Select("""
        <script>
        select o.orderId,o.userId,u.userName,o.orderTotal,o.orderDate
          from orders o
    	left join user u on o.userId=u.userId
            <where>
               <if test="key!=null">
                    o.userId like concat('%',#{key},'%')
                    or
                    u.userName like concat('%',#{key},'%')
                </if>
            </where>
        </script>
    """)
    public Page<OrderVo> selectByKeyPage(Page<OrderVo> page, @Param("key") String key);
}

