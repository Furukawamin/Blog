package blog.com.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.BlogsEntity;
import blog.com.services.BlogService;


@Controller
public class BlogEditController {
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/blog_edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId,Model model) {
		//Blog Edit purpose from blogService class(edit blog view)
		BlogsEntity blog = blogService.findEditByView(blogId);
		model.addAttribute("blogData",blog);
		return "blog_edit.html";
	}

	@PostMapping("/blog_delete")
	public String deleteBlog(@RequestParam Long blogId) {
		if(blogService.deleteByBlogId(blogId)) {
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
