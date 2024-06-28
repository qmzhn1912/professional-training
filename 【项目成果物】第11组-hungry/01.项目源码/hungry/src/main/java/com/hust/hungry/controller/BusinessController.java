package com.hust.hungry.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.Business;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.entity.User;
import com.hust.hungry.entity.vo.OrderVo;
import com.hust.hungry.mapper.BusinessMapper;
import com.hust.hungry.service.BusinessService;
import com.hust.hungry.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private UserService userService;

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

    @GetMapping("/{businessTypeId}")
    public JsonResult getBuisinessByorderTypePage(@RequestParam(value = "pn",defaultValue ="1" ,required = false) Integer pn,
                                              @RequestParam(value = "ps",defaultValue = "3",required = false) Integer ps,
                                              @PathVariable("businessTypeId") Integer orderTypeId){
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

    public static String generateSixDigitString() {
        // 定义可选的字符池
        String charPool = "0123456789";

        // 生成六位数的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomIndex = (int) (Math.random() * charPool.length());
            sb.append(charPool.charAt(randomIndex));
        }

        return sb.toString();
    }

    @PostMapping("/register")
        public ResponseEntity<User> registerBusiness(@RequestBody Business business,@Param("password") String password) {
        User user = new User();
        user.setUserName(business.getBusinessName());
        user.setPassword(password);
        String id = generateSixDigitString();
        user.setUserId(id);
        business.setUserId(id);
        business.setRemarks("open");
        user.setType(0);
        User users = userService.saveUser(user);
        businessService.saveBusiness(business);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @PutMapping("/update/{businessId}")
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

    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateBusinessStatus(@RequestBody Map<String, Object> requestBody) {
        Integer businessId = (Integer) requestBody.get("businessId");
        String remarks = (String) requestBody.get("remarks");

        int updatedRows = businessService.updateBusinessRemarksByBusinessId(businessId, remarks);

        if (updatedRows > 0) {
            return ResponseEntity.ok("Business status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update business status.");
        }
    }
}
