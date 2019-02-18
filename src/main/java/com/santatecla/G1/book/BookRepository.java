package com.santatecla.G1.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>{

	List<Book> findByTitle(String title);
	
}
