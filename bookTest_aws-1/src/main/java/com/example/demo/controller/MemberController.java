package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MemberController {
   @Autowired
   private MemberService ms;
   @Autowired
   private MemberDAO MemberDAO;

   @GetMapping("/member/login")
   public void loginForm() {      
   }
   
//   @PostMapping("/member/login")
//   public String loginProcess(@RequestParam("id") String id, 
//                              @RequestParam("pwd") String pwd) {
//       // 여기서는 간단히 사용자명과 비밀번호를 콘솔에 출력하는 예제를 보여줍니다.
//       System.out.println("Username: " + id);
//       System.out.println("Password: " + pwd);
//       
//       // 실제로는 로그인 처리 로직이 이곳에 들어가야 합니다.
//       // 예를 들어, 데이터베이스에서 사용자 정보를 조회하고 인증을 수행하는 등의 작업이 여기에서 이루어질 수 있습니다.
//       
//       // 로그인 처리 후에는 보통 다른 페이지로 리다이렉트하거나 적절한 응답을 반환합니다.
//       return "redirect:/book/list"; // 로그인 성공 후 홈 페이지로 리다이렉트
//   }
   
   @PostMapping("/member/login")
	public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
		String msg = "";
		ModelAndView mav = new ModelAndView("redirect:/book/list");
		Optional<Member> option = MemberDAO.findById(id);
		if(option.isPresent()) {
			String dbPwd = option.get().getPwd();
			if(pwd.equals(dbPwd)) {
				session.setAttribute("id", id);				
			}else {				
				mav.setViewName("redirect:/member/login");
			}
		}else {			
			mav.setViewName("redirect:/member/login");
		}
		
		return mav;
	}
   
   @GetMapping("/member/join")
   public void joinForm() {      
   }
   
   @PostMapping("/member/join")
   @ResponseBody
   public String joinSubmit(Member m) {
      ms.insert(m);
      return "OK";
   }
}
