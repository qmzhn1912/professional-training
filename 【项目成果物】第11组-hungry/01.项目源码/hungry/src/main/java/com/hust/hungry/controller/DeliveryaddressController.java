package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hust.hungry.entity.Deliveryaddress;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.mapper.DeliveryaddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryaddress")
public class DeliveryaddressController {
    @Autowired
    private DeliveryaddressMapper deliveryaddressMapper;
    @DeleteMapping("/del")
    public JsonResult del(@RequestParam("address") String address) {
        LambdaQueryWrapper<Deliveryaddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Deliveryaddress::getAddress,address);
        deliveryaddressMapper.delete(wrapper);
        return new JsonResult("成功删除");
    }
    @PostMapping("/add")
    public JsonResult add(@RequestBody Deliveryaddress deliveryaddress) {
        deliveryaddressMapper.insert(deliveryaddress);
        return new JsonResult("成功添加");
    }
    @PostMapping("/update")
    public JsonResult update(@RequestParam("address") String address,@RequestParam("updatedaddress") String updatedaddress) {
        UpdateWrapper<Deliveryaddress> wrapper = new UpdateWrapper<>();
        wrapper.eq("address",address).set("address",updatedaddress);
        deliveryaddressMapper.update(wrapper);
        return new JsonResult("成功更新");
    }
}

