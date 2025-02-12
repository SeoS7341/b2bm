package com.example.spring_boot_app.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_app.domain.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer>{
    public Config findById(int id);
}