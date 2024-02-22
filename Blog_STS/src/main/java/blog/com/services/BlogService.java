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
	
	//update blog content
	//Save,Update -->Use True,False
	//View,Entity --->Use Null
	//In blogcontroller 28-37 has design get blogId automatically
	public boolean updateBlog(Long blogId,Long userId,String blogTitle,String blogCategory,Date date,String article) {
		if (blogId == null) {
			return false;
		}else {
			//blogsRepo.save(new BlogsEntity(userId, blogCategory, blogTitle, date, photo, article,blogId));
			//編集前のデータを取得
			BlogsEntity blog =  blogsRepo.findByBlogId(blogId);
			blog.setArticle(article);
			blog.setBlogTitle(blogTitle);
			blog.setBlogCategory(blogCategory);
			blog.setDate(date);
			//blogID & userID are fix so no need to change
			blogsRepo.save(blog);
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
