package com.example.portoneT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortOneServiceImpl implements PortOneService {

	@Autowired
	PortOneDao dao;
	
	@Override
	public String insert(PortOneVO vo) {
		try {
			dao.insert(vo);
			return "/PortOneT/result";
		}catch(Exception e) {
			return "/PortOneT/failure";
		}
		
	}

	@Override
	public PortOneVO edit(PortOneVO vo) {
		return null;
	}

}
