package blog.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.BlogsEntity;
import jakarta.transaction.Transactional;

@Repository
public interface BlogsRepository extends JpaRepository<BlogsEntity, Long> {
	//Update and save blogs data
	BlogsEntity save(BlogsEntity blogsEntity);
	
	//Delete blogs
	//For Delete situation, better use void & int 
	@Transactional
	 int deleteByBlogId(Long blogId);
	 //void deleteById(Long blogId);
		
//	//Edit blog by Title
//		BlogsEntity editBlog(String blogTitle);
	
	//Find blogs by userId
	List<BlogsEntity> findByUserId(Long userId);
	
	//Get All Blog Data
	List<BlogsEntity>findAll();
	
	//Find blogs by blogtitle
	List<BlogsEntity> findByBlogTitleAndBlogCategory(String blogCategory, String blogTitle);
	
	//Find by blogId == html 53
	BlogsEntity findByBlogId(Long Id);
}
