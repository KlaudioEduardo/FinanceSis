package com.klaudiodev.financesis.financesis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klaudiodev.financesis.financesis.model.User;
import com.klaudiodev.financesis.financesis.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll(User user) {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

}
