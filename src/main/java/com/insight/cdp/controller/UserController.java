package com.insight.cdp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insight.cdp.payload.response.UserResponse;
import com.insight.cdp.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/get-all-users")
	public List<UserResponse> getAllUsers(){
		return service.getAllUsers();
	}
	
	@PostMapping("/make-admin/{userId}")
	public void makeAdmin(@PathVariable long userId) {
		service.makeAdmin(userId);
	}
	
	@DeleteMapping("/delete-user-by-id/{id}")
	public void deleteUserById(@PathVariable long id) {
		service.deleteUserById(id);
	}
}
