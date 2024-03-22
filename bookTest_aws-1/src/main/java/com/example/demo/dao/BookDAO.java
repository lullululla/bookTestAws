package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

import jakarta.transaction.Transactional;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

	@Query(value = "select * from book order by bookid", nativeQuery = true)
	public List<Book> findAllByOrderByBookid();
	
	@Query(value = "SELECT IFNULL(MAX(bookid), 0) + 1 FROM book", nativeQuery = true)
	public int getNextBookid();

	@Modifying
	@Query(value = "delete from book b where b.bookid=?1", nativeQuery = true)
	@Transactional
	public int deleteBook(int bookid);
}