package com.example.demo.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Repository
public interface MemberDAO extends JpaRepository<Member, String> {
	public Member findByIdAndPwd(String id, String pwd);
}
