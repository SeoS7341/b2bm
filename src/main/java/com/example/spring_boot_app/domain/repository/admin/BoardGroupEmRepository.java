package com.example.spring_boot_app.domain.repository.admin;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.spring_boot_app.common.util.CommonUtil;
import com.example.spring_boot_app.domain.BoardGroup;
import com.example.spring_boot_app.domain.BoardGroupList;

@Repository
public class BoardGroupEmRepository {

    @PersistenceContext
    EntityManager em;

    //보드그룹리스트
    public List<BoardGroupList> getAllBoardGroupsList(String prefix){
        String query="SELECT g.*,COUNT(gm.gm_id)as countAccessibleMembers ,COUNT(b.bo_table) as countIncludedBoards "+ //bo_table을 bo_id로 바꿔야함
                "FROM "+prefix+"group g LEFT JOIN "+ prefix+"group_member gm "+
                "ON g.gr_id=gm.gr_id "+
                "LEFT JOIN "+prefix+"board b "+
                "ON g.gr_id=b.gr_id "+
                "GROUP BY g.gr_id";

        return em.createNativeQuery(query, BoardGroupList.class).getResultList();
    }


    //그룹삭제
    @Transactional
    public int deleteGroups(String ids,String prefix){
        String query="delete from "+prefix+"group where gr_id in ("+ids+")";

        return em.createNativeQuery(query).executeUpdate();
    }

    //그룹생성,수정시 그룹목록
    public List<BoardGroup> getBoardGroupListByAdmin(){
        String is_admin="super";

        /*String query="SELECT g.gr_id AS id,g.gr_subject AS subject FROM "+CommonUtil.getTablePrefix()+"group g ";*/
        String query="SELECT g.* from "+CommonUtil.getTablePrefix()+"group g ";
        if(is_admin.equals("group")){
            query+=" LEFT JOIN "+CommonUtil.getTablePrefix()+"member m"
                    +" ON (m.mb_id=g.gr_admin) WHERE m.mb_id='"+"로긴한아이디"+"'";
        }
        /*	query+=" ORDER BY g.gr_id";*/

        System.out.println("query : " +query);

        return em.createNativeQuery(query,BoardGroup.class).getResultList();
    }







}