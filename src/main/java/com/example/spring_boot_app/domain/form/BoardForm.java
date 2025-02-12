package com.example.spring_boot_app.domain.form;

import lombok.Data;

@Data
public class BoardForm{

    private String selectedId;
    private int totalCount;
    private int pagePerPosts;
    private int currentPage;
    private String currentCategory;
    private int isReply;
    private int commentDepth;
    private int baseCommentId;
    private String boardName;
    private String saveType;

    public int redirectPageNumber(int deletedArticleCount, int pageNumber) {
        totalCount = totalCount - deletedArticleCount;
        if(totalCount % pagePerPosts > 0) {
            return pageNumber;
        } else {
            return totalCount / pagePerPosts;
        }
    }

}