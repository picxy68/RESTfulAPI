package com.example.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RestController {
	RestController(){
		System.out.println("RestController 생성자");
	}
	
	@GetMapping("/list")
	public  String  list(Model model, HttpServletRequest request, GuestBookVO vo ) throws Exception {
		String URL = "http://127.0.0.1:8895/Restful/list";
        String RESULTTYPE ="json" ;
        System.out.println("==> 확인: " +  vo.getCh1() + ":" + vo.getCh2());
        String ch1 ="";
        String ch2 ="";
        if (vo.getCh1() != null) {
            ch1 = request.getParameter("ch1") ;
            ch2 = request.getParameter("ch2") ;
        }else {
            ch1 = "" ;
            ch2 = "" ;
        }
        
		StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("ch1","UTF-8") + "=" + URLEncoder.encode(ch1, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("ch2","UTF-8") + "=" + URLEncoder.encode(ch2, "UTF-8")); 
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
       
        System.out.println(sb);

        try {
	       	 
            JSONParser jsonParser = new JSONParser();
            
            // JSON데이터를 넣어 JSON Object 로 만들어 준다.
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
             
            String pageSize = String.valueOf(jsonObject.get("pageSize")) ; 
            String pageListSize = String.valueOf(jsonObject.get("pageListSize")) ; 
            String totalCount = String.valueOf(jsonObject.get("totalCount")) ; 
            String totalPage = String.valueOf(jsonObject.get("totalPage")) ; 
            String start = String.valueOf(jsonObject.get("start")) ; 
             
            String currentPage = String.valueOf(jsonObject.get("currentPage")) ; 
            String listStartPage = String.valueOf(jsonObject.get("listStartPage")) ; 
            String listEndPage = String.valueOf(jsonObject.get("listEndPage")) ; 
            String lastPage = String.valueOf(jsonObject.get("lastPage")) ; 
            
            // items의 배열을 추출 
            JSONArray InfoArray = (JSONArray) jsonObject.get("li");

            System.out.println("* items *");

            List<GuestBookVO> li = new ArrayList<GuestBookVO>(); 
            
            for(int i=0; i< InfoArray.size(); i++){
            	GuestBookVO m = new GuestBookVO();
         	   
                System.out.println("=items_"+i+" ===========================================");
                 
                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject object = (JSONObject) InfoArray.get(i);
     
                m.setIdx(Integer.parseInt(String.valueOf(object.get("idx"))));
                m.setName( String.valueOf(object.get("name")));
                m.setMemo(String.valueOf(object.get("memo")));
                m.setAge(Integer.parseInt(String.valueOf(object.get("age"))));
                m.setRegdate(String.valueOf(object.get("regdate")));                
                li.add(m);      
                System.out.println("성공 ==>"+ i + ":" + m );
            } 
            
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("pageListSize", pageListSize);
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("start", start);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listStartPage", listStartPage);
            model.addAttribute("listEndPage", listEndPage);
            model.addAttribute("lastPage", lastPage);
            
            model.addAttribute("li", li);
   
       } catch (Exception e) {
           e.printStackTrace();
       }
		return "list";   	
	}

}
