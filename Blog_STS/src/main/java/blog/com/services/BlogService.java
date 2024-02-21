package blog.com.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.BlogsEntity;
import blog.com.repositories.BlogsRepository;

@Service
public class BlogService {
	@Autowired
	BlogsRepository blogsRepo;
	

	public boolean createBlog(Long userId,String blogCategory, String blogTitle, Date date, String photo,String article) {
		if (blogsRepo.findByBlogTitleAndBlogCategory(blogCategory, blogTitle).size()== 0) {
			blogsRepo.save(new BlogsEntity(userId,blogCategory,blogTitle,date,photo,article));
			return true;
		}else {
		return false;
	}
//		//Delete  blog
//		public List<BlogsEntity>deleteByBlogTitle(String blogTitle){
//			return blogsRepo.deleteByBlogTitle(blogTitle);
//		}
}
}
