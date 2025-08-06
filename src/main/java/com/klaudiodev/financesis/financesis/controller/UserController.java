package com.klaudiodev.financesis.financesis.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.klaudiodev.financesis.financesis.dto.UserDTO;
import com.klaudiodev.financesis.financesis.model.User;
import com.klaudiodev.financesis.financesis.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		if (userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body("Email já está em uso");
		}
		return ResponseEntity.ok(userService.save(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.findById(id);
		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
	    return ResponseEntity.ok(userService.findAllUsers());
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
		return userService.findById(id).map(user -> {
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setCpf(updatedUser.getCpf());
			userService.save(user);
			return ResponseEntity.ok(user);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
	    return userService.findByEmail(email)
	           .map(ResponseEntity::ok)
	           .orElse(ResponseEntity.notFound().build());
	}


}