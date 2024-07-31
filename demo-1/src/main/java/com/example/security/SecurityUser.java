package com.example.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.member.MemberVO;

public class SecurityUser extends User {

	private static final long serialVersionUID = 1L;

	public SecurityUser(MemberVO vo ) {
		super(vo.getUsername(), 
			  vo.getPassword(), 
			  AuthorityUtils.createAuthorityList(vo.getRole().toString()));
  	}	

}