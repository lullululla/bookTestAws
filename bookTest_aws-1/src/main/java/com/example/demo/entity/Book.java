package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class Book {
	@Id
	private int bookid;
	private String bookname;
	private String publisher;
	private int price;
	private String fname;
	
	@Transient
	private MultipartFile uploadFile;
}
