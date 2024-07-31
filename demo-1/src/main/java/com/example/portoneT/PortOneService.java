package com.example.portoneT;

import org.springframework.stereotype.Service;

@Service
public interface PortOneService {
	String insert(PortOneVO vo);
	PortOneVO edit(PortOneVO vo);
}
