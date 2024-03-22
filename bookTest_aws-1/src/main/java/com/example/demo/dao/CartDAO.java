package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cart;

@Repository
public interface CartDAO extends JpaRepository<Cart, Integer> {
	
	@Query(value = "select nvl(max(no),0)+1 from cart", nativeQuery = true)
	public int getNextNo();
	
	//아이디와 상품번호에 해당하는 cart를 조회하여 반환하는 쿼리메소드
	public Cart findByIdAndBookid(String id, int bookid);

	//아이디에 해당하는 고객이 담은 cart목록을 반환하는 쿼리메소드
	public List<Cart> findById(String id);
}












