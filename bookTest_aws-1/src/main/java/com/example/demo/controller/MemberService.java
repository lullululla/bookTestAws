package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Service
@Setter
public class MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	public MemberService() {
		System.out.println("MemberService 생성됨");
	}
	
	public void insert(Member m ) {
		memberDAO.save(m);
	}
		
}












