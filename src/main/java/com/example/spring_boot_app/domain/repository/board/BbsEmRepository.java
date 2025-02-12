package com.example.spring_boot_app.domain.repository.board;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.spring_boot_app.common.util.CommonUtil;
import com.example.spring_boot_app.domain.Write;

@Repository
public class BbsEmRepository {

    @PersistenceContext
    EntityManager em;

    // 글 삭제할 때 글에 포함된 댓글까지 함께 지우기 위해 댓글 id들까지 함께 가져온다.
    @SuppressWarnings("unchecked")
    public List<Write> findIdsWithCommentIds(String selectedIds) {
        String query = "select * from " + CommonUtil.getTablePrefix()
                + "write where wr_parent in (" + selectedIds + ")";
        List<Write> idList = em.createNativeQuery(query, Write.class).getResultList();
        return idList;
    }

    // 선택 삭제
    @Transactional
    public int delete(String ids) {
        String query = "delete from " + CommonUtil.getTablePrefix()
                + "write where wr_id in (" + ids + ")";
        return em.createNativeQuery(query, Write.class).executeUpdate();
    }

    // 이전 or 다음 글 찾기
    public int findPrevOrNextArticle(int articleNumber, String prevOrNext, int boardId) {
        String query = querySelect(prevOrNext);
        return findArticleNumber(query, articleNumber, boardId);
    }

    // 이전 or 다음 글 찾기 : 쿼리 선택
    private String querySelect(String prevOrNext) {
        String query = "";
        switch (prevOrNext) {
            case "prev":
                query = "SELECT MIN(w.id) FROM Write w WHERE w.id > :articleNumber AND w.isComment = 0 AND w.boardId = :boardId";
                break;
            case "next":
                query = "SELECT MAX(w.id) FROM Write w WHERE w.id < :articleNumber AND w.isComment = 0 AND w.boardId = :boardId";
                break;
        }
        return query;
    }

    // 이전 or 다음 글 찾기 : 쿼리 수행
    private int findArticleNumber(String query, int articleNumber, int boardId) {
        Object obj = em.createQuery(query)
                .setParameter("articleNumber", articleNumber)
                .setParameter("boardId", boardId)
                .getSingleResult();

        return CommonUtil.convertObjectToInteger(obj);
    }

    // 카테고리 리스트 가져오기
    @SuppressWarnings("unchecked")
    public List<String> findCategoryNames(int boardId) {
        String query = "SELECT DISTINCT(w.categoryName) as categoryName FROM Write w WHERE w.boardId = :boardId";
        return em.createQuery(query)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    // 게시판에서 가장 작은 wr_num 가져오기
    public int findMinNum() {
        String query = "SELECT MIN(w.num) FROM Write w";
        Object obj = em.createQuery(query).getSingleResult();
        return CommonUtil.convertObjectToInteger(obj);
    }

    // 게시판에서 가장 큰 wr_id 가져오기
    public int findMaxId() {
        String query = "SELECT MAX(w.id) FROM Write w";
        Object obj = em.createQuery(query).getSingleResult();
        return CommonUtil.convertObjectToInteger(obj);
    }

    // 해당 게시글에서 가장 큰 댓글 그룹 번호 가져오기
    public int findMaxCommentById(int articleNumber) {
        String query = "SELECT MAX(w.comment) FROM Write w WHERE w.parent=:articleNumber and w.isComment = 1";
        Object obj = em.createQuery(query)
                .setParameter("articleNumber", articleNumber)
                .getSingleResult();
        return CommonUtil.convertObjectToInteger(obj);
    }

    // 댓글이 들어갈 장소(commentReply)를 구한다.
    public String findMaxCommentReplyByBaseComment(int articleNumber, int comment, String commentReply, int isComment) {

        // query 조합
        String query = "SELECT MAX(w.commentReply) FROM Write w"
                + " WHERE w.parent=:articleNumber"
                + " AND w.isComment=:isComment"
                + " AND w.comment=:comment";
        if( !("").equals(commentReply) ) {
            query += " AND w.commentReply LIKE CONCAT(:commentReply, '%')";
        }
        // parameter 넣기
        Query q;
        if( !("").equals(commentReply) ) {
            q = em.createQuery(query)
                    .setParameter("articleNumber", articleNumber)
                    .setParameter("comment", comment)
                    .setParameter("isComment", isComment)
                    .setParameter("commentReply", commentReply);
        } else {
            q = em.createQuery(query)
                    .setParameter("articleNumber", articleNumber)
                    .setParameter("comment", comment)
                    .setParameter("isComment", isComment);
        }
        // query 실행
        Object obj = q.getSingleResult();

        return CommonUtil.convertObjectToString(obj);
    }

    // 답변글이 들어갈 장소(reply)를 구한다.
    public String findMaxReplyForAnswer(int num, String baseReply) {

        String query = "SELECT MAX(w.reply) FROM Write w"
                + " WHERE w.num=:num"
                + " AND w.reply LIKE CONCAT(:reply, '_')";

        Object obj = em.createQuery(query)
                .setParameter("num", num)
                .setParameter("reply", baseReply)
                .getSingleResult();

        return CommonUtil.convertObjectToString(obj);
    }

}