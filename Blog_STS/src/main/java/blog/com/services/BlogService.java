package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.repositories.BlogsRepository;

@Service
public class BlogService {
@Autowired
private BlogsRepository blogsRepo;


}
