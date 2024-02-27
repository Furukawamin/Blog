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
	//create or register new blog
	public boolean createBlog(Long userId, String blogCategory, String blogTitle, Date date, String photo,
			String article) {
		if (blogsRepo.findByBlogTitleAndBlogCategory(blogCategory, blogTitle).size() == 0) {
			blogsRepo.save(new BlogsEntity(userId, blogCategory, blogTitle, date, photo, article));
			return true;
		} else {

		}
		return false;
	}
	
	//delete blog 
	//suggest use boolean because no need to show delete data
	public boolean deleteByBlogId(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogsRepo.deleteByBlogId(blogId);
			return true;
		}
	}
	
	
	//view list of blog
	public List<BlogsEntity> findByUserId(Long userId){
		if(userId == null) {
			return null;
		}else {
			return blogsRepo.findByUserId(userId);
		}
	}
	
	//edit blog view
	public BlogsEntity findEditByView(Long Id) {
		if (Id == null) {
			return null;
		}else {
			return blogsRepo.findByBlogId(Id);
		}
	}
}
