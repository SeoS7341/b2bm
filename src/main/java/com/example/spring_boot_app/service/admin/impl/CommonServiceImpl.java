package com.example.spring_boot_app.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_boot_app.domain.repository.admin.CommonEmRepository;
import com.example.spring_boot_app.service.admin.CommonService;

@Service
public class CommonServiceImpl implements CommonService{

    private CommonEmRepository commonEmRepository;

    @Autowired
    public void setCommonEmRepository(CommonEmRepository commonEmRepository) {
        this.commonEmRepository = commonEmRepository;
    }

    @Override
    public List<?> search(String keyword, String searchWord, Object entity, String orderBy) {
        return commonEmRepository.search(keyword, searchWord, entity, orderBy);
    }

}