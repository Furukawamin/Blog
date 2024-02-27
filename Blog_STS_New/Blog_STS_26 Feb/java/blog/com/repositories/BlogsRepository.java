package blog.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import blog.com.models.BlogsEntity;
import jakarta.transaction.Transactional;

@Repository
public interface BlogsRepository extends JpaRepository<BlogsEntity, Long> {
	// Update and save blogs data
	BlogsEntity save(BlogsEntity blogsEntity);

	// Delete BLOG
	// **For Delete situation, better use void & int
	@Transactional
	int deleteByBlogId(Long blogId);
	// void deleteById(Long blogId);

	// Edit Blog by Title
	// BlogsEntity editBlog(String blogTitle);

	// Find BLOG by UserId ---> Refer BlogController 41 rows
	List<BlogsEntity> findByUserId(Long userId);

	// Get All Blogs's Data
	List<BlogsEntity> findAll();

	// Find Blog by blogTitle
	List<BlogsEntity> findByBlogTitleAndBlogCategory(String blogCategory, String blogTitle);

	// Find BlogId == html 53
	BlogsEntity findByBlogId(Long Id);

	// Search Blog
	List<BlogsEntity> searchByBlogId(Long blogId);

	// Show Specific Blog Where Contain Java --> Refer SearchController
//	@Query(value ="SELECT * "
//			+ "FROM blogs_entity "
//			+ "WHERE article "
//			+ "LIKE '%java%'",nativeQuery =true)
//	List <BlogsEntity> findByArticle(String query);

	// *ILIKE = can use for Upper Case and Lower Case
	@Query(value = "SELECT * FROM blogs_entity WHERE article ILIKE %:query%", nativeQuery = true)
	List<BlogsEntity> findByArticle(@Param("query") String query);

}