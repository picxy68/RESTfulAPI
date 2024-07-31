package com.example.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDao dao;
	
	@Override
	public void insert(GuestVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<GuestVO> list(GuestVO vo) {
		if(vo.getCh2()!=null) {
			if(!vo.getCh2().substring(0).equals("")) {
				String ch2 = '%'+vo.getCh2()+'%';
				vo.setCh2(ch2);
			}else {
				String ch2=vo.getCh2();
				vo.setCh2(ch2);
			}
		}
		
		return dao.list(vo);
	}

	@Override
	public int totalcount(GuestVO vo) {
		return dao.totalcount(vo);
	}

}
