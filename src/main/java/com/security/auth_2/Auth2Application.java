package com.security.auth_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Auth2Application {

	public static void main(String[] args) {
		SpringApplication.run(Auth2Application.class, args);
	}

}
