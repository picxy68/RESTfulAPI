package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.exam.ExamService;
import com.example.exam.ExamVO;

@Controller
public class ExamController {

	@Autowired
	ExamService service;
	
	@GetMapping("/exam/examForm")
	private String form() {
		return "exam/examForm.html";
	}
	
	@GetMapping("/exam/getExamList")
	private String getBoardList( Model  model, ExamVO vo) {
		model.addAttribute("li", service.getExamList(vo));
		return "exam/examList.html";
	}
	
}
