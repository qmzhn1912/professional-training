package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust.hungry.entity.Business;
import com.hust.hungry.entity.Food;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.Food;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
       @Select("""
        <script>
        select food.businessId
               from food
        <where>
           <if test="k!=null and k!=''">
                food.foodName like concat('%',#{k},'%')
            </if>
        </where>
        </script>
    """)
       List<Business> selectBusinessListByFoodName(@Param("k") String key);
}