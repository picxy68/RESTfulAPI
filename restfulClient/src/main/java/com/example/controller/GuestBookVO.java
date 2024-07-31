package com.example.controller;

import lombok.Data;

@Data
public class GuestBookVO {
	private int idx;
	private String name;
	private String memo;
	private int age;
	private String regdate;
	
	private String ch1;
	private String ch2;
}
