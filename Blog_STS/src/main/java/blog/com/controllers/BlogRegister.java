package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogRegister {

	@GetMapping("/blog_register")
	public String getBlogRegisterPage(){
		return "blog_register.html";
	}
}
