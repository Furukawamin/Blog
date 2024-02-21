package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
	@GetMapping("/blog")
	public String getBlogLoginPage() {
		return "blog.html";
	}
	

}
