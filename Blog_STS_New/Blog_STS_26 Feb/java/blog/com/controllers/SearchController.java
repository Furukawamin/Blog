package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blog.com.models.BlogsEntity;
import blog.com.repositories.BlogsRepository;
import blog.com.services.BlogService;

@Controller
//Note:To Response of Search Function
//Refer to BlogService 65 rows
public class SearchController {

	@Autowired
	private BlogService blogService;

	@GetMapping("blog_search")
	// 用戶輸入query //輸出：用戶會看到包含關鍵字的Blogs (blog.html)
	public ModelAndView searchBlogs(@RequestParam String query, ModelAndView mav) {
	//if (blogService.searchBlogs(query) != null) {
		
	// 把1個數據添加到頁面上
	//把多個數據送到頁面上
	List<BlogsEntity> list = blogService.searchBlogs(query);
		
	//可以檢測數據是否存在
	//System.out.println(list);
		
	//參考blog.HTML的59行
	mav.addObject("blogList", list);
			
	// mav.setViewName是顯示一個頁面給用戶
	mav.setViewName("blog.html");

//		} else {
//			mav.setViewName("blog_register.html");
//		}
		return mav;

	}
}
