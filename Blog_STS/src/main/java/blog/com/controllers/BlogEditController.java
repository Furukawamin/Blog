package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogEditController {
	@GetMapping("/blog_edit")
	public String getBlogEditPage(){
		return "blog_edit.html";
	}
}
