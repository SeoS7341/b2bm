package com.example.spring_boot_app.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_boot_app.domain.Config;
import com.example.spring_boot_app.domain.repository.admin.ConfigRepository;
import com.example.spring_boot_app.service.admin.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

    private ConfigRepository configRepository;

    @Autowired
    public void setConfigRepository(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public Config getConfig() {
        return configRepository.findById(1);
    }

}