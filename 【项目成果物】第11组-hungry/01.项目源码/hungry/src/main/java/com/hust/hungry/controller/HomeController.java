package com.hust.hungry.controller;

import com.hust.hungry.entity.Business;
import com.hust.hungry.entity.JsonResult;
import com.hust.hungry.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private BusinessService businessService;
    @PostMapping("/search")
    public JsonResult getbusinesslist(String key) {
        List<Business> businessList = businessService.getBusinessListByKey(key);
        return new JsonResult(businessList);
    }
}