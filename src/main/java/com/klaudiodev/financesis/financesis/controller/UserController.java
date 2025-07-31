package com.klaudiodev.financesis.financesis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klaudiodev.financesis.financesis.model.User;
import com.klaudiodev.financesis.financesis.service.UserService;

import lombok.Getter;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		if (userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body("Email ja está em uso.");
		}
		
		return ResponseEntity.ok(userService.save(user));
	}
	
	@GetMapping
	public List<User> getAllUsers() {
	    return userService.findAll();
	}
	
	

}
