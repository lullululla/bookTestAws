package com.example.demo.controller;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
   private MemberDAO memberDAO;

   @GetMapping("/member/login")
   public void loginForm() {      
   }
   @PostMapping("/member/login")
   public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
       ModelAndView mav = new ModelAndView();
       Optional<Member> optionalMember = memberDAO.findById(id);
       if (optionalMember.isPresent()) {
           Member member = optionalMember.get();
           if (pwd.equals(member.getPwd())) {
               session.setAttribute("id", id);
               mav.setViewName("redirect:/book/list");
           } else {
               mav.setViewName("redirect:/member/login");
           }
       } else {
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
