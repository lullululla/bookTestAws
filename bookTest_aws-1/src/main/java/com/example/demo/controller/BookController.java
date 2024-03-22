package com.example.demo.controller;


import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
public class BookController {

	@Autowired
	private BookService bs;

	@GetMapping("/book/list")
	public String list(Model model) {
		model.addAttribute("list", bs.listBook()); 
		return "/book/list";
	}
	
	@GetMapping("/book/insert")
	public void insertForm(@RequestParam(value = "bookid", defaultValue = "0")int bookid, Model model) {		
		model.addAttribute("bookid", bookid);
	}
	
	@PostMapping("/book/insert")
	public String insertSubmit(Book d, HttpServletRequest request) {
		String view = "redirect:/book/list";
		String path = request.getServletContext().getRealPath("/images");
		System.out.println("path:"+path);
		String filename = null;
		MultipartFile uploadFile = d.getUploadFile();
		filename = uploadFile.getOriginalFilename();
		d.setFname(filename);

		if(filename != null && !filename.equals("")) {
			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + filename);
				fos.write(data);
				fos.close();
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		bs.insert(d);
		return view;
	}

	
	@GetMapping("/book/delete/{bookid}")
	public String delete(@PathVariable("bookid") int bookid, HttpServletRequest request) {
		String view = "redirect:/book/list";
		String path = request.getServletContext().getRealPath("/images");
		String oldFname = bs.getBookid(bookid).getFname();
		System.out.println(oldFname);
		bs.delete(bookid);
		try {
			File file = new File(path +"/"+oldFname);
			file.delete();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return view;
	}
	
	@GetMapping("/book/update/{bookid}")
	public String update(@PathVariable("bookid") int bookid, Model model) {
		model.addAttribute("book", bs.getBookid(bookid));
		return "/book/update";
	}
	@PostMapping("/book/update")
	public String updateSubmit(Book d, HttpServletRequest request, Model model) {
		String view = "redirect:/book/list";
		String path = request.getServletContext().getRealPath("/images");
		System.out.println("path:"+path);
		String fname = null;
		String oldFname= d.getFname();
		d.setFname(oldFname);
		MultipartFile uploadFile = d.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		System.out.println("fname: "+fname);

		if(fname != null && !fname.equals("")) {   //첨부파일이 있는 경우
			try {
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);            
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
				d.setFname(fname);
				bs.update(d);
				File file = new File(path + "/" + oldFname);
				file.delete();
			}catch (Exception e) {
				System.out.println("파일 업로드 예외발생:"+e.getMessage());   
			}
		}else {   //첨부파일이 없는 경우
			bs.update(d);
		}
		return view;
	}
	
	@GetMapping("/book/detail/{bookid}")
	public String detail(@PathVariable("bookid") int bookid, Model model) {
		model.addAttribute("b", bs.getBookid(bookid));
		return "/book/detail";
	}
}
