package com.hust.hungry.mapper;

import com.hust.hungry.entity.vo.OrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void getOrderById(){
        OrderVo orderVo= orderMapper.selectById("1");
        System.out.println(orderVo);
    }

    @Test
    public void getOrderByKey(){
        //List<OrderVo> orderVos= orderMapper.selectByKey(null);
        List<OrderVo> orderVos= orderMapper.selectByKey("11");
        for (OrderVo v:orderVos) {
            System.out.println(orderVos);
        }
    }
}

