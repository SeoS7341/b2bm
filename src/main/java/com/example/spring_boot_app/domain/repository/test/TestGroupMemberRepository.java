package com.example.spring_boot_app.domain.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_app.domain.BoardGroupMember;

public interface TestGroupMemberRepository extends JpaRepository<BoardGroupMember, Integer>{

    BoardGroupMember findById(int id);

}