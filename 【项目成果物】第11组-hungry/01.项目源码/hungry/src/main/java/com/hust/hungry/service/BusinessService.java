package com.hust.hungry.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.hungry.entity.Business;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusinessService extends IService<Business> {
    List<Business> getBusinessListByKey(String key);
    Page<Business> getBusinessListByorderTypePage(Page<Business> page,Integer orderType);
}