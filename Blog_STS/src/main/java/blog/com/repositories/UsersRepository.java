package blog.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.UsersEntity;
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
	
	//Update and save user data
	UsersEntity save(UsersEntity usersEntiry);
	
	//Find user by email
	UsersEntity findByEmail(String email);
	
	//Get all user data
	List<UsersEntity> findAll();
	
	//Find user by email and password
	UsersEntity findByUsernameAndPassword(String username,String password);
}
