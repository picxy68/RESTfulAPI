package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.member.MemberService;
import com.example.member.MemberVO;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("/loginForm")
	String loginForm() {
		System.out.println("loginForm");
		return "member/loginForm";
	}
	
	//스프링부트 시큐리티 사용시 post로 정의. 스프링부트 시큐리티 적용시 사용하지 않는다.
	@PostMapping("/loginFormOK")
	String loginFormOK(MemberVO vo) {
		System.out.println("==>loginFormOK");
		MemberVO m = service.getLogin(vo);
		if (vo.getUsername().equals(m.getUsername())) {
			if (vo.getPassword().equals(m.getPassword()) ) {
				return "redirect:/login/loginSuccess";		
			}else {
				return "redirect:/login/fail";	
			}			
		}else {
			return "redirect:/login/fail";			
		}
	}
	
	//스프링부트 시큐리티 적용시 성공하면 매핑
	@GetMapping("/loginSuccess")
	String loginSuccess(MemberVO vo) {
		System.out.println("==>loginSuccess");
		return "member/loginSuccess.html";
	}
	//스프링부트 시큐리티 적용시 권한이 없는 경우 매핑
	@GetMapping("/accessDenied")
	String accessDenied(MemberVO vo) {
		System.out.println("==>accessDenied");
		return "member/accessDenied.html";
	}
	//스프링부트 시큐리티 적용시 실제 사용되지는 않는다.
		@GetMapping("/fail")
		String fail(MemberVO vo) {
			System.out.println("==>fail");
			return "member/fail.html";
		}
}
