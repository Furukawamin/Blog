package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import blog.com.models.BlogsEntity;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;


@Controller
public class BlogRegisterController {
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/blog_register")
	public String getBlogRegisterPage(){
		return "blog_register.html";
	}
	
	//Save register blog data
	@PostMapping("/blog_register")
	public String register(
							@RequestParam Long userId,
							@RequestParam String blogCategory,
							@RequestParam String blogTitle,
							@RequestParam Date date,
//							@RequestParam MultipartFile photo,
							@RequestParam String article) {
		
	//If create blog successfully go login page else go register page
	//"" is photo
	if (blogService.createBlog(userId,blogCategory, blogTitle, date,"",article)) {
				return "redirect:/blog";
			} else {
				return "redirect:/register";	
	}
	
	
}
}
