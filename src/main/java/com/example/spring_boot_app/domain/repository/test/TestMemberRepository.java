package com.example.spring_boot_app.domain.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring_boot_app.domain.Member;

public interface TestMemberRepository extends JpaRepository<Member, Integer>{
    @Query(value = "Select mb_no, mb_id from ahn.js_member where mb_id = :memberId", nativeQuery = true)		// native SQL
//	@Query(value = "Select m.id, m.no, m.name from Member m where m.id = :memberId")		// JPQL	: return type = String
    String findByMemberId(@Param("memberId") String id);

    Member findById(int no);
}