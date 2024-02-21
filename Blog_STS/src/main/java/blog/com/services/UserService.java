package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.UsersEntity;
import blog.com.repositories.UsersRepository;

@Service
public class UserService {
	//If findByEmail == null then will save into data
	//If not,will show false
	
	@Autowired
	private UsersRepository usersRepo;
	public boolean createUser(String username,String email,String password) {
		if(usersRepo.findByEmail(email)== null) {
			usersRepo.save(new UsersEntity(email,password,username));
			return true;
		}else {
		return false;
		}
	}
	
	//If findByUsername&Password == null --> Not login
	//If NOT NULL ---> Login
	public UsersEntity checkLogin(String username,String password) {
		UsersEntity usersEntity = usersRepo.findByUsernameAndPassword(username, password);
		if(usersEntity == null) {
			return null;
		}else {
			return usersEntity;
		}
	}

}
