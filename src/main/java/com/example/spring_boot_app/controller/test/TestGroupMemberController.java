package com.example.spring_boot_app.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring_boot_app.domain.BoardGroupMember;
import com.example.spring_boot_app.service.test.GroupMemberService;

@Controller
@RequestMapping("/test/groupMember")
public class TestGroupMemberController {

    private GroupMemberService groupMemberService;

    @Autowired
    public void setGroupMemberService(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    // test query method - group member
    @RequestMapping(value="/view/GroupMemberById", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String findMemberByNoUseQueryMethod(Model model) {
        BoardGroupMember gMember = groupMemberService.findMemberByNo(1);
        model.addAttribute("gMember", gMember);
        return "test3";
    }

    // test - group member
    @RequestMapping(value="/allGroupMember", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String findFirstMember(Model model) {
        List<BoardGroupMember> allGroupMember = groupMemberService.AllGroupMember();
        model.addAttribute("group_member", allGroupMember);
        return "test2";
    }
}