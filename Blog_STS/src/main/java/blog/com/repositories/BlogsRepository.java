package blog.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.BlogsEntity;
@Repository
public interface BlogsRepository extends JpaRepository<BlogsEntity, Long> {
	//Update and save blogs data
	BlogsEntity save(BlogsEntity blogsEntity);
	
	//Find blogs by username
	BlogsEntity findByUserId(Long userId);
	
	//Get All Blog Data
	List<BlogsEntity>findAll();
	
	//Find blogs by blogtitle
	List<BlogsEntity> findByBlogTitleAndBlogCategory(String blogCategory, String blogTitle);
	
	//Delete blogs
	boolean deleteByBlogTitle(String blogTitle);
}
