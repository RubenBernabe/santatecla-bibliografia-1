package com.santatecla.G1.citation;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.book.Book;
import com.santatecla.G1.book.BookRepository;
import com.santatecla.G1.theme.Theme;


@Entity
public class Citation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length=1000)
	private String text;
	
	/********************************************
	 * RELATIONS WITH OTHER CLASES TO DDBB MODEL
	 ********************************************/
	
	//To avoid cicles on DB model
	
	@OneToOne()
	private Book book;
	@OneToOne
	private Theme theme;
	
	

	/********************************************
	 * METHODS OF THE CLASS
	 ********************************************/
	
	//Constructor to Spring
	public Citation() {}
	

	public Citation(String text , Book book) {
		super();
		this.text = text;
		this.book = book;
		this.theme = book.getTheme();
	}
	
	public Citation(String text) {
		super();
		this.text = text;
		//this.book = null;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@Override
	public String toString() {
		return "Cita: "+this.text;
	}
	
	
}
