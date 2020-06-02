package net.codejava;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	// password generator: Run As Java App., to generate the encoded password & update it to DB password field.	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		// String rawPass = "admin"; 
		 String rawPass = "dimi"; 
		// String rawPass = "dimi"; 
		String encodedPass = encoder.encode(rawPass); 
		
		System.out.println(encodedPass); 
	}

}
