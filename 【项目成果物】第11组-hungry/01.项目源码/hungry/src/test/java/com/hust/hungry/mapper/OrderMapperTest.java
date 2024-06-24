package com.hust.hungry.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.Orders;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

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

        @Test
    public void getOrderByKeyPage(){
        Page<Orders> page = new Page<>(1,3);
        Page<Orders> orders= orderService.page(page);
        System.out.println( new JsonResult(orders));
    }
}

