package com.example.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServcieImpl implements BoardService{

	BoardServcieImpl(){
		System.out.println("BoardServcieImpl 생성자");
	}
	@Autowired
	private BoardDao dao;
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
	//	System.out.println("getBoardList(vo)"+vo);
		String ch2=vo.getCh2();
		vo.setCh2("%"+ch2+"%");
		return dao.getBoardList(vo);
	}
	
	@Override
	public int insert(BoardVO vo) {
		dao.insert(vo);
		return dao.totalCount(vo);
	}

	@Override
	public BoardVO boardEdit(BoardVO vo) {
		return dao.boardEdit(vo);
	}

	@Override
	public int update(BoardVO vo) {
		return dao.update(vo);
	}

	@Override
	public int delete(BoardVO vo) {
		return dao.delete(vo);
	}

	
	
}
