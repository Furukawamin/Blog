package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class LoginController {
	
	//Access Login Page
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
	

//Get & Post are same . If user wan to edit something in blog only use Post
//	@PostMapping("/blog")
//	public String blogPage(@RequestParam String username,@RequestParam String password) {
//		return "blog.html";
//	}

}
