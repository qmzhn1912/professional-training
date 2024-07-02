package com.hust.hungry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import com.hust.hungry.entity.Business;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BusinessMapper extends BaseMapper<Business> {
     @Select("""
        <script>
        select b.businessName,b.businessId,b.businessAddress,b.businessExplain,b.businessImg,b.orderTypeId,b.startPrice,b.deliveryPrice
          from business b
        <where>
           <if test="k!=null and k!=''">
                b.businessName like concat('%',#{k},'%')
            </if>
        </where>
        </script>
    """)
    List<Business> selectBusinessListByBusinessName(@Param("k") String key);


      @Select("""
        <script>
        select b.businessName,b.businessId,b.businessAddress,b.businessExplain,b.businessImg,b.orderTypeId,b.startPrice,b.deliveryPrice
          from business b
        <where>
             b.orderTypeId = #{orderTypeId}
        </where>
        </script>
    """)
      Page<Business> selectBusinessListByorderTypePage(Page<Business> page,@Param("orderTypeId") Integer orderTypeId);

    @Select("""
        <script>
        select b.businessName, b.businessId, b.businessAddress, b.businessExplain, b.businessImg, b.orderTypeId, b.starPrice, b.deliveryPrice
        from business b
        <where>
           <if test="orderTypeId != null">
                b.orderTypeId = #{orderTypeId}
            </if>
        </where>
        order by b.score desc
        </script>
    """)
    List<Business> selectBusinessListByOrderTypeIdOrderByScore(@Param("orderTypeId") Integer orderTypeId);

    @Update("UPDATE business SET remarks = #{remarks} WHERE businessId = #{businessId}")
    int updateBusinessRemarksByBusinessId(@Param("businessId") Integer businessId, @Param("remarks") String remarks);
}