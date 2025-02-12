package com.example.spring_boot_app.domain.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_app.domain.BoardFile;

@Repository
public interface FileRepository extends JpaRepository<BoardFile, Integer>{

    // 업로드된 파일정보 가지고 오기
    List<BoardFile> findByWriteIdAndBoardId(int id, int boardId);

}