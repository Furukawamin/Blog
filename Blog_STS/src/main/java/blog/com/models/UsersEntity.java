package blog.com.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class UsersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String username;
	private String email;
	private String password;
	private String phone_number;
	

	public UsersEntity(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
	
	
	
}



