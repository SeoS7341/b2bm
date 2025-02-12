package com.example.spring_boot_app.service.test;

import java.util.List;

import com.example.spring_boot_app.domain.Member;

public interface MemberService {
    public String findMemberById(String memberId);
    public List<Member> findAll();
}