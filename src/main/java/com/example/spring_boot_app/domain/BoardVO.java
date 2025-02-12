package com.example.spring_boot_app.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

//게시판리스트에서 게시판을 다중 수정하기 위해 만들어둔 클래스.
@EqualsAndHashCode(callSuper = false)
@Data
public class BoardVO extends Board{

    private List<BoardVO> list;


}