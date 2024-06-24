package com.hust.hungry.controller;

import com.hust.hungry.entity.Business;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.service.BusinessService;
import com.hust.hungry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    @GetMapping("/list")
    public JsonResult getBusinessesByKey(@RequestParam("key")String key) {
        List<Business> businessList = businessService.getBusinessListByKey(key);
        return new JsonResult(businessList);
    }
}
