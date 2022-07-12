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
import org.springframework.web.bind.annotation.PostMapping;

import com.ashleybattles.bookclub.models.Author;
import com.ashleybattles.bookclub.models.LoginUser;
import com.ashleybattles.bookclub.models.User;
import com.ashleybattles.bookclub.repo.UserRepo;
import com.ashleybattles.bookclub.services.AuthorService;
import com.ashleybattles.bookclub.services.UserService;



@Controller
public class UserController {
	
	//bring in service
	@Autowired
	private UserService userServ;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private AuthorService authorServ;
	
	//display 
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("userId") != null) {
			
			return "redirect:/books";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "index.jsp";
	}
 
	@GetMapping("/books")
	public String home(Model model, HttpSession session ) {
		//check if user is logged in
		if(session.isNew() || session.getAttribute("uuid")==null) {
			return "redirect:/";
		}
		//logged in user
		model.addAttribute("user",userServ.getOne((Long)session.getAttribute("uuid")));
		//get all books
		List<Author> authors = authorServ.getAll();
		model.addAttribute("authors", authors);
		return "home.jsp";
	}
	
	
	//action
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
		User user = userServ.register(newUser, result);
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        
        session.setAttribute("uuid", user.getId());
    
        return "redirect:/books";
    }
	
	
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
    	User user = userServ.login(newLogin, result);
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("uuid", user.getId());
    
        return "redirect:/books";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("uuid");
    	return "redirect:/";
    }
    
    

}
