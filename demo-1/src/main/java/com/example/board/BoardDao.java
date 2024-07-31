package com.example.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
	List<BoardVO> getBoardList(BoardVO vo);
	int insert(BoardVO vo);
	BoardVO boardEdit(BoardVO vo);
	int update(BoardVO vo);
	int delete(BoardVO vo);
	
	int totalCount(BoardVO vo);
}