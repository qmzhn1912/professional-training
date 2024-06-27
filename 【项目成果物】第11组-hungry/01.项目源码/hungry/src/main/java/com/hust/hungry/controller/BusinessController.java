package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.Business;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.mapper.BusinessMapper;
import com.hust.hungry.service.BusinessService;
import com.hust.hungry.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessMapper businessMapper;

    @PostMapping("/appraise/{score}/{businessId}")
    public void appraise(@PathVariable("score")Float score,@PathVariable("businessId")Integer businessId){
        Business business = businessMapper.selectById(businessId);
        business.setAppraiseNum(business.getAppraiseNum()+1);
        business.setScore((business.getAppraiseNum()* business.getScore()+score)/business.getAppraiseNum());
        businessMapper.updateById(business);
    }
    @GetMapping("/search")
    public JsonResult getBusinessesByKey(@RequestParam("key")String key) {
        List<Business> businessList = businessService.getBusinessListByKey(key);
        return new JsonResult(businessList);
    }

    @GetMapping("/getInfo/{businessId}")
    public JsonResult getInfo(@PathVariable("businessId")Integer businessId) {
        Business business= businessMapper.selectById(businessId);
        return new JsonResult(business);
    }

    @GetMapping("/{orderTypeId}")
    public JsonResult getBuisinessByorderTypePage(@RequestParam(value = "pn",defaultValue ="1" ,required = false) Integer pn,
                                              @RequestParam(value = "ps",defaultValue = "3",required = false) Integer ps,
                                              @PathVariable("orderTypeId") Integer orderTypeId){
        //1、取  参数列表
        //2、调
        Page<Business> page = new Page<>(pn,ps);
//        List<OrderItem> orderItems=new ArrayList<>();
//        OrderItem oitem=new OrderItem();
//        oitem.setColumn("o.orderId");
//        oitem.setAsc(false);
//        orderItems.add(oitem);
//        page.setOrders(orderItems);
//        page=orderService.getOrderByKeyPage(page,key);
        page = businessService.getBusinessListByorderTypePage(page,orderTypeId);
        return new JsonResult(page);
    }


    @GetMapping("/byscore")
    public List<Business> getBusinessListByOrderTypeIdOrderByScore(@RequestParam Integer orderTypeId) {
        return businessService.getBusinessListByOrderTypeIdOrderByScore(orderTypeId);
    }

    @PostMapping("/register")
    public ResponseEntity<Business> registerBusiness(@RequestBody Business business) {
        Business savedBusiness = businessService.saveBusiness(business);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBusiness);
    }

    @PostMapping("/update/{businessId}")
     public JsonResult update(@RequestParam(value = "businessName") String businessName,
                              @RequestParam(value = "starPrice") Float starPrice,
                              @RequestParam(value = "businessAddress") String businessAddress,
                              @RequestParam(value = "deliveryPrice") Float deliveryPrice,
                              @PathVariable("businessId")Integer businessId) {
        Business business = businessMapper.selectById(businessId);
        business.setBusinessName(businessName);
        business.setStarPrice(starPrice);
        business.setBusinessAddress(businessAddress);
        business.setDeliveryPrice(deliveryPrice);
        businessMapper.updateById(business);
        return new JsonResult(business);
    }
}
