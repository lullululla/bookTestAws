package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;

import lombok.Setter;

@Service
@Setter
public class BookService {
	@Autowired
	private BookDAO dao;

	public List<Book> listBook(){
		return dao.findAllByOrderByBookid();
	}
	public void insert(Book b) {
		b.setBookid(dao.getNextBookid());
		dao.save(b);
	}
	public int delete(int bookid){
		return dao.deleteBook(bookid);
	}
	public Book getBookid(int bookid) {
		return dao.findById(bookid).get();
	}
	public void update(Book d){
		dao.save(d);
	}
}
