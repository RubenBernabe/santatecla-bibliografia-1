package com.santatecla.G1.book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santatecla.G1.user.UserComponent;

@Controller
public class BooksController {

	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private BookRepository repository;
	
	public Collection<Book> books(){
		return repository.findAll();
	}
	
	@RequestMapping("/book/{id}")
	public String Book(Model model, @PathVariable long id) {
		Optional<Book> book = repository.findById(id);
		System.out.println(book.toString());
		if (book!=null) {
			model.addAttribute("book", book.get());
		}
		return "booksPage";
	}
	
	@RequestMapping("/newBook")
	public String newBook(Model model) {
		
		return "booksPageEdit";
	}
	
	@RequestMapping("/saveBook")
	public String saveBook(Model model, Book book) {
		repository.save(book);
		model.addAttribute("text","Book Created");
		return "Message";
	}
	
	@RequestMapping("book/{{id}}/updateBook")
	public String updateBook(Model model, @PathVariable long id, Book book) {
		repository.deleteById(id);
		repository.save(book);		
		model.addAttribute("text","Book Edit correctly");
		return "Message";
	}
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			//model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	

}
