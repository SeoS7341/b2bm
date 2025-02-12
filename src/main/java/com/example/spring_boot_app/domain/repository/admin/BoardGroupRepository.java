package com.example.spring_boot_app.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_app.domain.BoardGroup;

import java.util.Optional;

public interface BoardGroupRepository extends JpaRepository<BoardGroup, String>{

    public Optional<BoardGroup> findById(String id);
}