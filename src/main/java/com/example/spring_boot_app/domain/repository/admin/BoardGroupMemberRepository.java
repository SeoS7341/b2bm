package com.example.spring_boot_app.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_app.domain.BoardGroupMember;

@Repository
public interface BoardGroupMemberRepository extends JpaRepository<BoardGroupMember, Integer> {

/*	@Modifying
	@Query("select count(gm.id) from BoardGroupMember gm where gm.id=?1")
	public int getCountAccessibleMembers(String id);*/
}