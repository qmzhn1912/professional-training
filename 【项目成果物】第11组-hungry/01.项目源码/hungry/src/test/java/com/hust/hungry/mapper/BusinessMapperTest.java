package com.hust.hungry.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hust.hungry.entity.Business;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BusinessMapperTest {

    @Autowired
    private BusinessMapper businessMapper;

    @Test
    public void getBusinessById(){

        LambdaQueryWrapper<Business> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //查询条件
        lambdaQueryWrapper.eq(Business::getBusinessId,10001);
        //查询列
        lambdaQueryWrapper.select(true,Business::getBusinessId,Business::getBusinessName);

        Business user= businessMapper.selectOne(lambdaQueryWrapper);
        System.out.println(user);
    }
}
