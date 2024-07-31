package com.example.guestbook;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestDao {
	void insert(GuestVO vo);
	List<GuestVO> list(GuestVO vo);
	int totalcount(GuestVO vo);
	
}
