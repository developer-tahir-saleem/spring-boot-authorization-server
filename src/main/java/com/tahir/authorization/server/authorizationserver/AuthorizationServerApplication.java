package com.tahir.authorization.server.authorizationserver;

import com.tahir.authorization.server.authorizationserver.model.User;
import com.tahir.authorization.server.authorizationserver.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableResourceServer
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}


	@Autowired
	private CustomUserDetailsService accountService;

	@GetMapping("publica")
	public String publica() {
		String msg = String.format("Welcome To Public Auth API");
		return msg;
	}
	@RequestMapping("/validateUser")
	public Principal user(Principal user) {
		return user;
	}
	@GetMapping(path = "whoami", produces = "application/json")
	public User me() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return accountService.findAccountByUsername(username);
	}
}
