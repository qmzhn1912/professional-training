package com.hust.hungry.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.Orderdetailet;
import com.hust.hungry.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.hust.hungry.entity.vo.OrderVo;
import java.util.List;

@Service
public interface OrderService extends IService<Orders> {
    public OrderVo getOrderById(String id);

    public List<OrderVo> getOrderByKey(String key);

    public Page<OrderVo> getOrderByKeyPage(Page<OrderVo> page, String key);

    public  List<Orders> getOrdersByUserId(String userId);

    public List<Orderdetailet> getOrderdetailetByOrderId( String userId);

    public  List<Orders> getOrdersByBusinessId(int BusinessId);
}