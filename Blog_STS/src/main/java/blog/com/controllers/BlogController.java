package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.com.models.BlogsEntity;
import blog.com.models.UsersEntity;
import blog.com.services.BlogService;
import blog.com.services.UserService;

@Controller
@RequestMapping
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/blog")
	public String getBlogLoginPage(Model model) {
		//record user login info (we know username only and we need to find userId)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//get login username
		String username = auth.getName();
		
		//We know username and we need to find userId by username
		UsersEntity user = userService.selectByUsername(username);
		Long userId = user.getUserId();
		
		//from service's list of blog to see blog list
		List<BlogsEntity>blogList = blogService.findByUserId(userId);
//		System.out.println(blogList);
		model.addAttribute("blogList",blogList);
			
		
		return "blog.html";
	}

	
	

}
