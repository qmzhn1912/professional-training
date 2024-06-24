package com.hust.hungry.mapper;

import com.hust.hungry.entity.Business;
import com.hust.hungry.entity.vo.OrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FoodMapperTest {

    @Autowired
    private FoodMapper foodMapper;

    @Test
    public void getOrderByKey(){
        List<Business> businessList= foodMapper.selectBusinessListByFoodName("黄");
        for (Business b:businessList) {
            System.out.println(b);
        }
    }
}
