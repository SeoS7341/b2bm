package com.example.spring_boot_app.service.test.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_boot_app.domain.BoardGroupMember;
import com.example.spring_boot_app.domain.repository.test.TestGroupMemberRepository;
import com.example.spring_boot_app.service.test.GroupMemberService;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    private TestGroupMemberRepository groupMemberRepository;

    @Autowired
    public void setGroupMemberRepository(TestGroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    // findMemberByNo()
    public BoardGroupMember findMemberByNo(int id) {
        return groupMemberRepository.findById(id);
    }

    // GroupMember findAll()
    public List<BoardGroupMember> AllGroupMember() {
        return groupMemberRepository.findAll();
    }
}
