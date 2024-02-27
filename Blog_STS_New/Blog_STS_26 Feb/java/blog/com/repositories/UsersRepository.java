package blog.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.UsersEntity;
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
	
	//Register New User
	UsersEntity save(UsersEntity usersEntiry);
	
	//Find User by Email 
	//User fir UserService 22 rows
	UsersEntity findByEmail(String email);
	
	//Find All User TO GET USER's DATA 
	List<UsersEntity> findAll();
	
	//Find User by Username & Password from UserEntity
	//Use for UserService 34 rows
	UsersEntity findByUsernameAndPassword(String username,String password);

	//Find Username from UserEntity
	//Use for UserService 57 rows
	UsersEntity findByUsername (String username);
	

}
