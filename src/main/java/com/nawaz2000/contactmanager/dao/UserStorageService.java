package com.nawaz2000.contactmanager.dao;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nawaz2000.contactmanager.entity.User;

@Service
public class UserStorageService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	public User saveUser(MultipartFile file, User user) throws IOException {
		// If a file is provided, set it as the user's image
		if (file != null && !file.isEmpty()) {
			user.setImage(file.getBytes());
		} else if (user.getId() != null) {
			// If no file is uploaded and the user has an ID, fetch the existing image from the database
			Optional<User> existingUserOpt = userRepository.findById(user.getId());
			if (existingUserOpt.isPresent()) {
				User existingUser = existingUserOpt.get();
				// Retain the existing image
				user.setImage(existingUser.getImage());
			}
		}
		// Save the user entity (with or without a file)
		return userRepository.save(user);
	}
	
	public Optional<User> findUserById(Integer id) {
		if (id != null) {
			return userRepository.findById(id);
		}
		return Optional.empty(); // Return an empty Optional if the ID is null
	}
	
	public Optional<User> findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}
}
