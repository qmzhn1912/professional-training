package com.hust.hungry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.hungry.entity.Business;
import com.hust.hungry.mapper.BusinessMapper;
import com.hust.hungry.service.BusinessService;
import org.springframework.stereotype.Service;

@Service
public class BusinessImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
}