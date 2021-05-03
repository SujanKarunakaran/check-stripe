package com.sujan.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sujan.demo.model.User;
import com.sujan.demo.repositories.UserRepository;
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
		
	public ResponseEntity<User> createUser(User user) {
		try {
			User userNew = userRepository.save(new User(user.getUsername(), user.getEmail(), 
					user.getPassword()));
		    return new ResponseEntity<>(userNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<List<User>> listUsers() {
		try {
		    List<User> users = new ArrayList<User>();
		    userRepository.findAll().forEach(users::add);
		    if (users.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<User> getUserById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<User> updateUser(String id, User user) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User userOld = userData.get();
			userOld.setUsername(user.getUsername());
			userOld.setEmail(user.getEmail());
			userOld.setPassword(user.getPassword());
		    return new ResponseEntity<>(userRepository.save(userOld), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deleteUser(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<User> getUserByUsername(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
