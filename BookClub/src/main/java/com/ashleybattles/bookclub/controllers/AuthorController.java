package com.ashleybattles.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashleybattles.bookclub.models.Author;
import com.ashleybattles.bookclub.models.User;
import com.ashleybattles.bookclub.services.AuthorService;
import com.ashleybattles.bookclub.services.UserService;


@Controller
public class AuthorController {
	
	@Autowired
	private AuthorService authorServ;
	
	@Autowired
	private UserService userServ;
	
	//get all books // check to see if user is logged in
//	@GetMapping("/books")
//	public String home(HttpSession session, Model model) {
//		if(session.getAttribute("userId") != null) {
//		return "redirect:/";
//	}
//		List<Author> authors = authorServ.getAll();
//		model.addAttribute("authors", authors);
//		return "home.jsp";
//		
//	}
	
	
	@GetMapping("/author/new")
	public String newAuthor(@ModelAttribute("newAuthor") Author author, Model model, HttpSession session) {
		if(session.isNew() || session.getAttribute("uuid")==null) {
			return "redirect:/";
		}
		//logged in user
		model.addAttribute("user",userServ.getOne((Long)session.getAttribute("uuid")));
		
		//get a new book
		Author newAuthor = new Author();
		model.addAttribute("author", newAuthor);
		
			return "createauth.jsp";
		
	}
	
	//add book to database
	@PostMapping("/author/new")
	public String createAuthor(@Valid @ModelAttribute("newAuthor") 
			Author authorobj, 
			BindingResult result, 
			Model model, 
			HttpSession session) {
		//check is user in session
		if(session.isNew() || session.getAttribute("uuid")==null) {
			return "redirect:/";
		}
		//logged in user
		User user = userServ.getOne((Long)session.getAttribute("uuid"));
		model.addAttribute("user", user);
		if(result.hasErrors()) {
//			System.out.println(result);
			model.addAttribute("authors", authorServ.getAll());
			return "createauth.jsp";
		}else {
			//create the book
			System.out.println(authorobj.getUser());
			//will connect the user to the author in the DB
			authorobj.setUser(user);
			Author addbook = authorServ.save(authorobj);
			return "redirect:/books";
		}
	}
	
	//details of the book
	
	@GetMapping("/books/{bookId}")
	public String show(Model model, @PathVariable("bookId") Long authorId, HttpSession session) {
//		System.out.println(authorId);
		if(session.isNew() || session.getAttribute("uuid")==null) {
			return "redirect:/";
		}
//		System.out.println(book);
		model.addAttribute("user",userServ.getOne((Long)session.getAttribute("uuid")));
//		ArrayList<Book_Model> books = bookService.allBooks();
		Author book = authorServ.getOne(authorId);
		model.addAttribute("book", book);
		return "show.jsp";
	}
	
//EDIIT / UPDATE WHICH SHARES .SAVE
	@GetMapping("/books/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.isNew() || session.getAttribute("uuid")==null) {
			return "redirect:/";
		}
		model.addAttribute("user",userServ.getOne((Long)session.getAttribute("uuid")));
		
        model.addAttribute("editAuthor", authorServ.getOne(id));
        return "editauth.jsp";
    }
	
	@PutMapping("/books/{id}/edit")
	public String update(@Valid @ModelAttribute("editAuthor") Author editAuthor, BindingResult result, @PathVariable("id") Long id,Model model, HttpSession session) {
		if(session.isNew() || session.getAttribute("uuid")==null) {
			return "redirect:/";
		}
		model.addAttribute("user",userServ.getOne((Long)session.getAttribute("uuid")));
		model.addAttribute("editAuthor", authorServ.getOne(id));
		if(result.hasErrors()) {
//			System.out.println(editAuthor.getId());
			return "editauth.jsp";
		}else {
			authorServ.save(editAuthor);
			return "redirect:/books/" + editAuthor.getId();
			
		}
	}
    
   

}
