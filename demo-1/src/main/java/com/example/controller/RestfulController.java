package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.guestbook.GuestService;
import com.example.guestbook.GuestVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/Restful")
@RestController
public class RestfulController {
	@Autowired
	private GuestService  service;
	
	@GetMapping("/list")
    public String getGuestBookList( GuestVO vo , Model model ) {
		System.out.println("===> list ");
    	//start, ch1, ch2, pageSize 4가지 값은 Client Controller 쪽에서 넘겨준것을 받아서 사용한다.
		int start = 0;
		int pageSize = 10;
		int pageListSize = 10;
		
		int totalCount = service.totalcount(vo);
		
		if (vo.getStart() ==0) {
			start = 1 ;
		}else {
			start = vo.getStart();
		}
		
		int  end = start + pageSize - 1 ;
		int  totalPage =(int) (Math.ceil((double) totalCount / pageSize));  // 전체페이지 수 
		int  currentPage = (start / pageSize) + 1;  // 현재페이지 
		
		int  lastPage = (totalPage - 1) * pageSize + 1;  // 마지막 페이지
		
	    int  listStartPage = (currentPage - 1) / pageListSize * pageListSize + 1;   // 하단 번호 시작
	    int  listEndPage = listStartPage + pageListSize - 1;   // 하단 번호 끝
		
        /*
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("pageListSize", pageListSize);
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("start", start);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("listStartPage", listStartPage);
	    model.addAttribute("listEndPage", listEndPage);
	    model.addAttribute("lastPage", lastPage);
	    */
	    vo.setStart(start);
	    vo.setEnd(end);
		model.addAttribute("li",service.list(vo));
		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageSize", pageSize);
		resultMap.put("pageListSize", pageListSize);
		resultMap.put("totalCount", totalCount);
		resultMap.put("totalPage", totalPage);
		resultMap.put("start", start);
		resultMap.put("currentPage", currentPage);
		resultMap.put("listStartPage", listStartPage);
		resultMap.put("listEndPage", listEndPage);
		resultMap.put("lastPage", lastPage);
		resultMap.put("li", service.list(vo));
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return  mapper.writeValueAsString(resultMap);
		}catch( Exception e ){
			e.printStackTrace();
			return null;    
		}		
					
    }


}
