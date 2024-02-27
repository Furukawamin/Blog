package blog.com.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
//Note: To register new blog

public class BlogRegisterController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@GetMapping("/blog_register")
	public String getBlogRegisterPage() {
		return "blog_register.html";
	}

	// Save Blog data
	@PostMapping("/blog_register")
	public String register(@RequestParam Long userId, @RequestParam String blogCategory, @RequestParam String blogTitle,
			@RequestParam Date date, @RequestParam ("blogPhoto") MultipartFile blogPhoto, @RequestParam String article) throws IOException {

		// If Create Blog Successfully go Login Page Else Go Register Page
		// "" is photo
		String fileName = blogPhoto.getOriginalFilename();
		//Tell Where Is Image Path
		File blogFile = new File("./src/main/resources/static/blog-img/"+fileName);
		byte[]bytes = blogPhoto.getBytes();
		//Back Up Image if the Image has Save
		BufferedOutputStream out = new BufferedOutputStream (new FileOutputStream(blogFile));
		out.write(bytes);
		out.close();
		if (blogService.createBlog(userId, blogCategory, blogTitle, date,fileName, article)) {
			return "redirect:/blog";
		} else {
			return "redirect:/register";
		}
	}

}
