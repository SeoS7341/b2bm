package com.example.spring_boot_app.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring_boot_app.service.board.BbsService;

@Controller("indexControllerBoard")
@RequestMapping(value="/")
public class IndexController {

    private BbsService bbsService;

    @Autowired
    public void setBbsService(BbsService bbsService) {
        this.bbsService = bbsService;
    }

    @RequestMapping(value={"","index"})
    public String index(Model model) {

        model = bbsService.getNewArticleList(model);

        return "/board/index";
    }
}