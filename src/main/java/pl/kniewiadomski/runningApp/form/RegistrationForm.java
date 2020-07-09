package pl.kniewiadomski.runningApp.form;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import pl.kniewiadomski.runningApp.entity.User;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		
		return new User(username, passwordEncoder.encode(password));
	}
}
