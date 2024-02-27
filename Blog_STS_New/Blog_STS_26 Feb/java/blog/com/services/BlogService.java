package blog.com.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.BlogsEntity;
import blog.com.repositories.BlogsRepository;

@Service
public class BlogService {
	@Autowired
	BlogsRepository blogsRepo;

	//Register New Blog ---> BlogRegister 43 rows
	public boolean createBlog(Long userId, String blogCategory, String blogTitle, Date date, String fileName,
			String article) {
		if (blogsRepo.findByBlogTitleAndBlogCategory(blogCategory, blogTitle).size() == 0) {
			blogsRepo.save(new BlogsEntity(userId, blogCategory, blogTitle, date,fileName, article));
			return true;
		} else {

		}
		return false;
	}

	// Delete Blog By blogId ---> Refer BlogEditController 34 rows
	// suggest use boolean because no need to show delete data
	public boolean deleteByBlogId(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogsRepo.deleteByBlogId(blogId);
			return true;
		}
	}

	// Update Blog Content ---> Refer BlogEditController 48 rows
	// Save,Update -->Use True,False
	// View,Entity --->Use Null
	// In BlogController 28-37 has design get blogId automatically
	public boolean updateBlog(Long blogId, Long userId, 
								String blogTitle, String blogCategory, 
								Date date,String article,String fileName) {
		if (blogId == null) {
			return false;
		} else {
			// blogsRepo.save(new BlogsEntity(userId, blogCategory, blogTitle, date, photo,
			// article,blogId));
			// 編集前のデータを取得
			BlogsEntity blog = blogsRepo.findByBlogId(blogId);
			blog.setArticle(article);
			blog.setBlogTitle(blogTitle);
			blog.setBlogCategory(blogCategory);
			blog.setDate(date);
			blog.setPhoto(fileName);
			// blogID & userID are fix so no need to change
			blogsRepo.save(blog);
			return true;
		}
	}

	// Search Blog by Query Keyword ---> SearchController 
	// 返回 //動作 //動作的輸入
	public List<BlogsEntity> searchBlogs(String query) {
		// 定義方法需要String，使用不需要String
		List<BlogsEntity> blogs = blogsRepo.findByArticle(query);
		return blogs;

	}

	//Blog's View List ---> Refer BlogController 41 row
	public List<BlogsEntity> findByUserId(Long userId) {
		if (userId == null) {
			return null;
		} else {
			return blogsRepo.findByUserId(userId);
		}
	}

	// Edit Blog View ---> BlogEditController 27 rows
	public BlogsEntity findEditByView(Long Id) {
		if (Id == null) {
			return null;
		} else {
			return blogsRepo.findByBlogId(Id);
		}
	}


}
