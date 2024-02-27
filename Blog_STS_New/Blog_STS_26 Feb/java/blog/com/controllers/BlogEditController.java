package blog.com.controllers;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.BlogsEntity;
import blog.com.services.BlogService;


@Controller
public class BlogEditController {
	@Autowired
	private BlogService blogService;
	
	//Access to Blog Edit Page
	@GetMapping("/blog_edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId,Model model) {
		//Blog Edit purpose from blogService class(edit blog view)
		BlogsEntity blog = blogService.findEditByView(blogId);
		model.addAttribute("blogData",blog);
		return "blog_edit.html";
	}
	
	//Delete Blog by blogId ---> BlogService 
	@PostMapping("/blog_delete")
	public String deleteBlog(@RequestParam Long blogId) {
		if(blogService.deleteByBlogId(blogId)) {
			return "redirect:/blog";
		}else {
			return "redirect:/blog_edit/"+blogId;
		}
	}
//			else {
//		return "redirect:/blog_edit/"+blogId;
//		}
//	}
	
	//Edit Blog by All Blog's Info ---> BlogService 44 rows
	@PostMapping("/blog_edit")
	public String updateBlog(@RequestParam Long blogId,
							@RequestParam Long userId,
							@RequestParam String blogTitle,
							@RequestParam String blogCategory,
							@RequestParam MultipartFile blogPhoto,
							@RequestParam Date date,
							@RequestParam String article) throws IOException {
		// If Create Blog Successfully go Login Page Else Go Register Page
		// 
		
		String fileName = blogPhoto.getOriginalFilename();
		//Tell Where Is Image Path     
		//Upload photo save in blog-img
		//fileName is photo
		//In blog.html 62 rows change to <img th:src="'/blog-img/' + ${blog.photo}" alt="">
		File blogFile = new File("./src/main/resources/static/blog-img/"+fileName);
		byte[]bytes = blogPhoto.getBytes();
		//Back Up Image if the Image has Save
		BufferedOutputStream out = new BufferedOutputStream (new FileOutputStream(blogFile));
		out.write(bytes);
		out.close();
																					
		if(blogService.updateBlog(blogId,userId,blogTitle,blogCategory,date,article,fileName)) {
			return "redirect:/blog";
		}else {
			return "redirect:/blog_edit/"+blogId;
		}
	
	}
	
	//from service'list of blog to delete blog list
	/*public BlogsEntity deleteByBlogId(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogService.deleteByBlogId(blogId);
		}
	}*/
}
