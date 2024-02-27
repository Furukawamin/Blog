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
//Note:View blogs
//Use Username to Find Out UserId & Use UserID to Find Out Blogs

public class BlogController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private UserService userService;

	@GetMapping("/blog")
	public String getBlogLoginPage(Model model) {
		// 爲了得到用戶名
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		// 用username找userId
		UsersEntity user = userService.selectByUsername(username);
		Long userId = user.getUserId();

		// 用UserID（service）找多個blog
		List<BlogsEntity> blogList = blogService.findByUserId(userId);
		// System.out.println(blogList);
		model.addAttribute("blogList", blogList);

		return "blog.html";
	}

}
