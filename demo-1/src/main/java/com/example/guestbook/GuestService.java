package com.example.guestbook;

import java.util.List;

public interface GuestService {
	void insert(GuestVO vo);
	List<GuestVO> list(GuestVO vo);
	int totalcount(GuestVO vo);
}
