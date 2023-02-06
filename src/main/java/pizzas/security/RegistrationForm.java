package pizzas.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import pizzas.User2;

@Data
public class RegistrationForm {
	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	public User2 toUser(PasswordEncoder passwordEncoder) {
		return new User2(
				username, passwordEncoder.encode(password),
				fullname, street, city, state, zip, phone);
	}
}