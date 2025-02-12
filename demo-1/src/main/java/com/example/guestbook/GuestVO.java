package com.example.guestbook;

import lombok.Data;

@Data
public class GuestVO {

	private int idx;
	private String name;
	private String memo;
	private int age;
	private String regdate;
	
	private int rownum;
	private int rnum;
	private String ch1;
	private String ch2;
	
	private int start;
	private int end;
}
