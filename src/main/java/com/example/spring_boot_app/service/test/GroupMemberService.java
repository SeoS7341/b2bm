package com.example.spring_boot_app.service.test;

import java.util.List;

import com.example.spring_boot_app.domain.BoardGroupMember;

public interface GroupMemberService {
    public BoardGroupMember findMemberByNo(int id);
    public List<BoardGroupMember> AllGroupMember();
}