package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("com.example.demo")
public class AiPoweredPersonalFinanceTracker1Application {

	public static void main(String[] args) {
		
		SpringApplication.run(AiPoweredPersonalFinanceTracker1Application.class, args);
		System.out.println("git demo");
	}
	

//	@Autowired
//    private PasswordEncoder encoder;
//	
//	
//	// i have used this for password generation for admin...remember in every machiine different code is generated
//	@PostConstruct
//	public void generatePassword() {
//	    System.out.println("ENCODED = " + encoder.encode("dev123"));
//	}
}


