package com.example.member;

import lombok.Data;

@Data
public class MemberVO {
	private String id;
	private String username;
	private String pwd;
	private String password;
	private String role;
	private String grade;
	private String files;
	private String regdate;
}
