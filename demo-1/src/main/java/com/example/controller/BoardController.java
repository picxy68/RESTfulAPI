package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.BoardService;
import com.example.board.BoardVO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/board/")
@Controller
public class BoardController {

	
	@Autowired
	BoardService service;
	
	@GetMapping("form")
	private String form() {
		return "board/form.html";
	}
	
	@GetMapping("insert")
	private String insert( Model  model, BoardVO vo) {
		System.out.println("insert 매핑"+vo);
		int totalCount = service.insert(vo);
		model.addAttribute("totalCount", totalCount);
		return "redirect:/board/getBoardList";
	}
	
	@GetMapping("getBoardList")
	private String getBoardList( Model  model, BoardVO vo) {
		System.out.println("getBoardList 매핑");
		model.addAttribute("li", service.getBoardList(vo));
		return "board/getBoardList.html";
	}
	
	@GetMapping("boardEdit")
	private String boardEdit(Model  model, BoardVO vo) {
	//	System.out.println("boardEdit 매핑");
		model.addAttribute("m", service.boardEdit(vo));
		return "board/boardEdit.html";
	}
	
	@GetMapping("update")
	private String update( Model  model, BoardVO vo) {
		System.out.println("update 매핑"+vo);
		model.addAttribute("m", service.update(vo));
		return "redirect:/board/getBoardList";
	}
	
	@GetMapping("delete")
	private String delete(BoardVO vo) {
		service.delete(vo);
		System.out.println("삭제된 vo:"+vo);
		return "redirect:/board/getBoardList";
	}
}
