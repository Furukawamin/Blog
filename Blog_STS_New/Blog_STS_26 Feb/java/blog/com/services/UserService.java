package blog.com.services;

import java.security.Security;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.BlogsEntity;
import blog.com.models.UsersEntity;
import blog.com.repositories.UsersRepository;

@Service
public class UserService {
	// If findByEmail == null then will save into data
	// If not,will show false

	@Autowired
	private UsersRepository usersRepo;

	public boolean createAccount(String name, String email, String phone, String password) {
		if (usersRepo.findByEmail(email) == null) {
			usersRepo.save(new UsersEntity(name, email, password, phone));
//			Security.addusersRepo(email,password);
			return true;
		} else {
			return false;
		}
	}

	// LOGIN --> If Username & Password exist
	//NOT LOGIN ---> If findByUsername&Password == null 
	//Noted: Refer from UsersRepository 24 rows
	public UsersEntity login(String username, String password) {
		UsersEntity usersEntity = usersRepo.findByUsernameAndPassword(username, password);
		if (usersEntity == null) {
			return null;
		} else {
			return usersEntity;
		}
	}

	// Get All User Information
	public List<UsersEntity> getAllAccounts() {
		return usersRepo.findAll();
	}

	// Get ID by Email 
	//Refer UsersRepository 17 rows
	public UsersEntity selectById(String email) {
		return usersRepo.findByEmail(email);
	}

	// Get Username by selectByUsername for UserController
	public UsersEntity selectByUsername(String username) {
		if(username == null) {
			return null;
		}else {
			return usersRepo.findByUsername(username);
		}
	}

//	// Get userinfo by username for UserController
//	public List<UsersEntity> findByUsername(String username) {
//		List<UsersEntity> users = usersRepo.findByUsername(username);
//		return users;
//	}
}
