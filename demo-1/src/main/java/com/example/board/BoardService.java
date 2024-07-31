package com.example.board;

import java.util.List;

public interface BoardService {
	List<BoardVO> getBoardList(BoardVO vo);
	int insert(BoardVO vo);
	BoardVO boardEdit(BoardVO vo);
	int update(BoardVO vo);
	int delete(BoardVO vo);
}
