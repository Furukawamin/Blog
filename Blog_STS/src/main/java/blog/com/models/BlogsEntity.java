package blog.com.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BlogsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;
	private Long userId;
	private String blogCategory;
	private String blogTitle;
	private Date date;
	private String photo;
	private String article;
	
	public BlogsEntity() {
		super();
	}

	public BlogsEntity(Long userId, String blogCategory, String blogTitle, Date date, String photo, String article) {
		super();
		this.userId = userId;
		this.blogCategory = blogCategory;
		this.blogTitle = blogTitle;
		this.date = date;
		this.photo = photo;
		this.article = article;
	}
	
	
	
	
	
	

	
}
