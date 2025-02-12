package com.example.spring_boot_app.controller.admin;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring_boot_app.common.util.AdminUtil;
import com.example.spring_boot_app.domain.Board;
import com.example.spring_boot_app.domain.BoardGroup;
import com.example.spring_boot_app.domain.BoardVO;
import com.example.spring_boot_app.domain.Config;
import com.example.spring_boot_app.service.admin.BoardService;
import com.example.spring_boot_app.service.admin.ConfigService;

import java.util.Optional;

@Controller
@RequestMapping("/adm/board")
public class BoardController {


    private BoardService boardService;
    private AdminUtil adminUtil;
    private ConfigService configService;

    @Autowired
    public void setBoardService(BoardService boardService){
        this.boardService=boardService;
    }

    @Autowired
    public void setAdminUtil(AdminUtil adminUtil){
        this.adminUtil=adminUtil;
    }

    @Autowired
    public void setConfigService(ConfigService configService){
        this.configService=configService;
    }


    //게시판 목록
    @RequestMapping(value={"/list","/"})
    public String boardsList(Model model){

        //보드 갯수
        model.addAttribute("countBoards",boardService.getCountBoards());

        model.addAttribute("allBoardGroupsList", boardService.getAllBoardsList());
        return "admin/board/list";
    }




    //보드 그룹 목록 출력
    @RequestMapping(value={"/boardgroupslist"})
    public String boardgroupsList(Model model){

        model.addAttribute("countBoardGroupsList",boardService.getCountBoardGroups());
        model.addAttribute("allBoardGroupsList", boardService.getAllBoardGroupsList());
        return "admin/board/group_list";
    }


    //그룹추가폼
    @RequestMapping(value={"/form/addgroup"})
    public String ShowaddGroupForm(Model model){

        model.addAttribute("type","add");
        return "admin/board/groupform";

    }

    // 그룹 수정 폼
    @RequestMapping(value = {"/form/updategroup/{groupId}"})
    public String showUpdateGroupForm(Model model, @PathVariable("groupId") String groupId) {
        model.addAttribute("type", "update");

        // Optional 처리
        Optional<BoardGroup> optionalBoardGroup = boardService.getOneBoardGroup(groupId);

        // 값이 존재하면 boardGroup에 저장, 없으면 예외 발생
        BoardGroup boardGroup = optionalBoardGroup.orElseThrow(() -> new RuntimeException("BoardGroup not found"));

        model.addAttribute("boardGroup", boardGroup);
        model.addAttribute("countAccessibleMembers", boardService.getCountAccessibleMembers(boardGroup.getId()));

        return "admin/board/groupform";
    }

    //그룹추가
    @RequestMapping(value={"/addgroup"},method=RequestMethod.POST)
    public String addGroup(Model model,BoardGroup group,HttpServletRequest request ){
        boardService.addBoardGroup(group);
        System.out.println("여기는 보드그룹추가메서드:"+request.getMethod());
        return "redirect:./boardgroupslist";
    }


    //그룹수정
    @RequestMapping(value={"/updategroup"},method=RequestMethod.PUT)
    public String updateGroup(Model model,BoardGroup group,HttpServletRequest request){
        boardService.addBoardGroup(group);
        System.out.println("여기는 보드그룹수정메서드:"+request.getMethod());
        return "redirect:./boardgroupslist";
    }


    //그룹리스트에서 그룹삭제
    @RequestMapping(value={"/delete/group"},method=RequestMethod.DELETE)
    public String deleteGroup(Model model, @RequestParam("chk[]") String chk){

        boardService.deleteGroups(chk);
        return "redirect:../boardgroupslist";
    }


    //게시판 추가폼
    @RequestMapping(value={"/form/add"})
    public String showAddBoardForm(Model model){

        Config config = configService.getConfig();

        model.addAttribute("config",config);
        model.addAttribute("board",boardService.getInitializedBoard(config));

        model.addAttribute("type","add");

        model.addAttribute("countBoardGroups",boardService.getCountBoardGroups());
        //selectBox의 id, 현재그룹 id, 필수여부
        model.addAttribute("selectedGroupTag",boardService.getSelectedGroup("groupId", "", "required"));

        model.addAttribute("listLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("listLevel", 1, 10, 1,null));
        model.addAttribute("leadLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("leadLevel", 1, 10, 1,null));
        model.addAttribute("writeLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("writeLevel", 1, 10, 1,null));
        model.addAttribute("replyLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("replyLevel", 1, 10, 1,null));
        model.addAttribute("commentLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("commentLevel", 1, 10, 1,null));
        model.addAttribute("linkLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("linkLevel", 1, 10, 1,null));
        model.addAttribute("uploadLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("uploadLevel", 1, 10, 1,null));
        model.addAttribute("downloadLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("downloadLevel", 1, 10, 1,null));
        model.addAttribute("htmlLevelSelectTag", adminUtil.getMemberLevelSelectBoxTag("htmlLevel", 1, 10, 1,null));

        model.addAttribute("editorContentHead",adminUtil.editorHtml("contentHead",""));
        model.addAttribute("editorContentTail",adminUtil.editorHtml("contentTail",""));
        model.addAttribute("editormobileContentHead",adminUtil.editorHtml("mobileContentHead", ""));
        model.addAttribute("editorMobileContentTail",adminUtil.editorHtml("mobileContentTail", ""));


        return "admin/board/form";
    }

    //게시판 수정폼
    @RequestMapping(value={"/form/update"})
    public String showUpdateBoardForm(Model model){

        model.addAttribute("selectedGroupTag",boardService.getSelectedGroup("groupId", "", "required"));
        model.addAttribute("type","update");
        return "admin/board/form";
    }

    // 게시판 추가
    @RequestMapping(value={"/add"},method=RequestMethod.POST)
    public String addBoard(Board board){



        boardService.addBoard(board);
        return "redirect:./list";
    }


    //폼에서 게시판 수정
    @RequestMapping(value={"/update"}, method=RequestMethod.PUT)
    public String updateBoard(Board board){
        boardService.addBoard(board);
        return "admin/board/list";
    }


    //리스트에서 게시판 수정
    @RequestMapping(value={"/updateordelete"},method=RequestMethod.PUT)
    public String updateBoards(String[] chk,@ModelAttribute BoardVO boardVO){
        boardService.updateBoards(chk,boardVO);
        return "admin/board/list";
    }

    //리스트에서 게시판 삭제
    @RequestMapping(value={"/updateordelete"},method=RequestMethod.DELETE)
    public String deleteBoards(String[] chk,@ModelAttribute BoardVO boardVO){
        System.out.println("여긴 딜리트 : " + chk);
        boardService.deleteBoards(chk,boardVO);
        return "admin/board/list";
    }


}