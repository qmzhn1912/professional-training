package com.hust.hungry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.hungry.entity.Business;
import com.hust.hungry.mapper.FoodMapper;
import com.hust.hungry.mapper.BusinessMapper;
import com.hust.hungry.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Autowired
    private FoodMapper foodMapper;
    @Override
    public List<Business> getBusinessListByKey(String key) {
        System.out.println("111");
        List<Business> businessListSearchedByName = new ArrayList<Business>();
        List<Business> businessListId = foodMapper.selectBusinessListByFoodName(key);
        System.out.println(businessListId);
        List<Business> businessListSearchedByBusinessName = baseMapper.selectBusinessListByBusinessName(key);
        businessListSearchedByName.addAll(businessListSearchedByBusinessName);
        List<Integer> businessIDSet = new ArrayList<Integer>();;
        for (Business business : businessListSearchedByName) {
            businessIDSet.add(business.getBusinessId());
        }
        System.out.println(businessIDSet);
        for (Business business : businessListId) {
            Integer businessID = business.getBusinessId();
            if (!businessIDSet.contains(businessID)){
                businessListSearchedByName.add(baseMapper.selectById(businessID));
            }
        }
        return businessListSearchedByName;
    }
}