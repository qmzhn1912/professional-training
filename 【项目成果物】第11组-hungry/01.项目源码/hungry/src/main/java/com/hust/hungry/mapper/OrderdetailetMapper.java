package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust.hungry.entity.Orderdetailet;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.entity.vo.OrderdetailetVo;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.Orderdetailet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderdetailetMapper extends BaseMapper<Orderdetailet> {
    @Select("""
        <script>
        select o.orderId,o.odId,f.foodName,f.foodPrice,o.foodId,o.quantity
          from orderdetailet o
    	left join food f on o.foodId=f.foodId
       <where>
           o.orderId=#{orderId}
        </where>
        </script>
    """)
    List<OrderdetailetVo>selectOrderdetailetVoList (@Param("orderId") Integer orderId);
}