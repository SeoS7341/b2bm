package com.example.spring_boot_app.service.admin;

import java.util.List;

import com.example.spring_boot_app.domain.Member;
import com.example.spring_boot_app.domain.MemberGroupCount;
import com.example.spring_boot_app.domain.Point;
import com.example.spring_boot_app.domain.PointBaseEntity;
import com.example.spring_boot_app.domain.PointJoinMember;

public interface MemberService {

    //???
    public List<Member> getAllMembers();

    //탈퇴한 회원 명수
    public String getCountRetiredMembers();

    //차단된 회원 명수
    public String getCountBlockedMembers();

    //모든 회원 명수
    public long getCountAllMembers();

    //모든 회원정보
    public List<MemberGroupCount>getAllMembersList();

    //회원한명검색
    public Member getOneMemer(String memberId);

    //관리자가 회원 추가,수정
    public void adminSavesMember(Member member,String isCertify);

    //관리자가 회원 삭제
    public void adminDeletesMembers(String id);

    //관리자가 회원정보수정
    public void adminUpdatesMember(Member member);

    //포인트관리 총 건수
    public long getCountPointlist();

    //모든 회원 포인트 합계
    public int getTotalPoint();

    //모든 회원 포인트 내역
    public List<PointJoinMember> getAllPointContent();

    //관리자가 회원에게 포인트 추기
    public String addPoint(Point point);

    //관리자가 포인트 리스트를 삭제
    public void deletePointlist(int id);


}