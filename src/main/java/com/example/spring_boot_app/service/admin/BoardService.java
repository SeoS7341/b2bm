package com.example.spring_boot_app.service.admin;

import java.util.List;
import java.util.Optional;

import com.example.spring_boot_app.domain.Board;
import com.example.spring_boot_app.domain.BoardGroup;
import com.example.spring_boot_app.domain.BoardGroupList;
import com.example.spring_boot_app.domain.BoardVO;
import com.example.spring_boot_app.domain.Config;

public interface BoardService {


    //보드 그룹 리스트 가져오기
    public List<BoardGroupList> getAllBoardGroupsList();


    //보드 그룹 전체 개수
    public long getCountBoardGroups();

    //보드그룹 추가
    public void addBoardGroup(BoardGroup group);


    //보드그룹 한개 가져오기
    public Optional<BoardGroup> getOneBoardGroup(String id);

    //그룹에 접근 가능한 회원수
    public int getCountAccessibleMembers(String id);

    //그룹삭제
    public int deleteGroups(String ids);

    //게시판 목록
    public List<Board> getAllBoardsList();

    //게시판 생성,수정시 그룹목록 출력과 생성시에는 해당하는 그룹 선택까지 한 selectBOX 태그
    public String getSelectedGroup(String name,String groupId,String event);

    //생성된 게시판 갯 수
    public long getCountBoards();

    //보드 추가
    public void addBoard(Board board);

    //보드 수정
    public void updateBoards(String[] chk,BoardVO boardVO);

    //보드 삭제
    public void deleteBoards(String[] chk,BoardVO boardVO);


    public Board getInitializedBoard(Config config);
}