package com.example.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.guestbook.GuestService;
import com.example.guestbook.GuestVO;

@RequestMapping("/guestbook")
@Controller
public class GuestBookController {
	
	@Autowired
	private GuestService service;
	
	GuestBookController(){
		System.out.println("GuestBookController 생성자");
	}
	
	@GetMapping("/insert")
	public String insert(GuestVO vo) {
		System.out.println("==>insert");
		String [] firstName ={"최","박","석","김","황","오","이","장","정","조", "한"};		
		String [] LastName ={"하니","두리","지효","지솔","태익","지희","민준","승현","민기","민영"};
	    
		int [] age = {11,12,13,14,15,16,17};
		String [] memo = {"감사합니다.","잘부탁드려요","너무 좋아요","응원합니다.",
				          "진심으로 감사합니다.","정말 마음에 들어요.","정말 만족스러워요."
				          ,"너무 마음에 들어요.","힘내세요, 응원합니다.","항상 응원할게요."};
		
		Random random = new Random();
		
		for (int i =1 ;i <=125 ; i++) { 
			int firstArr = random.nextInt(11);
			int LastArr = random.nextInt(10);
			int ageArr = random.nextInt(7);
			int memoArr = random.nextInt(10);
			
			vo.setName( firstName[firstArr] + LastName[LastArr] );
			vo.setAge(age[ageArr]);
			vo.setMemo(memo[memoArr]);
		
		service.insert(vo);
		}
		return "redirect:/guestBook/list";    	
	}
	
	@GetMapping("/list")
	public String list(GuestVO vo, Model model) {
		System.out.println("==>list");
		
		int start=0;
		int pageSize=10;
		int pageListSize=10;
		
		int totalCount=service.totalcount(vo);
		
		if(vo.getStart()==0) {
			start = 1;
		}else {
			start=vo.getStart();
		}
		
		int end = start+pageSize-1;
		int totalPage=(int) (Math.ceil((double)totalCount/pageSize)); //전체 페이지수
		int currentPage = (start/pageSize)+1; //현재페이지
		int lastPage=(totalPage-1) * pageSize +1; //마지막페이지
		int listStartPage = (currentPage-1)/pageListSize * pageListSize +1; //하단 번호 시작
		int listEndPage = listStartPage + pageListSize-1; //하단번호 끝
		
		//1.페이지 사이즈
		model.addAttribute("pageSize", pageSize);
		//2.페이지 list사이즈
		model.addAttribute("pageListSize", pageListSize);
		//3.전체레코드 수
		model.addAttribute("totalCount", totalCount);
		//4.총 페이지 수
		model.addAttribute("totalPage", totalPage);
		//5.현재레코드
		model.addAttribute("start", start);
		//6.현재 페이지
		model.addAttribute("currentPage", currentPage);
		//7.하단가로 시작
		model.addAttribute("listStartPage", listStartPage);
		//8.하단 가로 끝
		model.addAttribute("listEndPage", listEndPage);
		
		vo.setStart(start);
		vo.setEnd(end);
		
		model.addAttribute("ch1", vo.getCh1());
		model.addAttribute("ch2", vo.getCh2());
		model.addAttribute("li", service.list(vo));
		return "guestbook/list.html";
	}
	
	
}
