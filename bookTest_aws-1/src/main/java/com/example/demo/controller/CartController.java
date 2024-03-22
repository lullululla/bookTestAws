package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class CartController {
	@Autowired
	private CartService cs;
	
	@GetMapping("/cart/list")
	public void list(Model model, HttpSession session) {
		String id = ((Member)session.getAttribute("id")).getId();
		
		model.addAttribute("list", cs.listCart(id));
	}
	
	
	@GetMapping("/cart/add/{no}")
	public String add(@PathVariable("no") int no, HttpSession session) {
	    // 세션에서 사용자 아이디 가져오기
	    String id = (String) session.getAttribute("id");
	    if (id == null) {
	        // 로그인되지 않은 사용자
	        return "redirect:/member/login";
	    }

	    // 장바구니에 상품 추가
	    Cart c = new Cart();
	    c.setId(id);
	    c.setBookid(no);
	    cs.insert(c);

	    return "/cart/add";
	}
}









