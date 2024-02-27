package blog.com.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.repositories.UsersRepository;
import blog.com.services.UserService;
	
@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}
	
	@PostMapping("/register")
	public String register(@RequestParam String name,@RequestParam String email,
			              @RequestParam String password,@RequestParam String phone) {
		if (userService.createAccount(name,email,phone,password)) {
			//No need add html after return
			return "redirect:/login";
		}else{
			return "redirect:/register";
		}
	}
}
