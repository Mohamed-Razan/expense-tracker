package com.insight.cdp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.insight.cdp.model.ERole;
import com.insight.cdp.model.Role;
import com.insight.cdp.model.User;
import com.insight.cdp.payload.response.UserResponse;
import com.insight.cdp.repository.RoleRepository;
import com.insight.cdp.repository.UserRepository;
import com.insight.cdp.serviceInterface.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

	private UserRepository repository;
	private RoleRepository roleRepository;

	public UserService(UserRepository repository, RoleRepository roleRepository) {
		this.repository = repository;
		this.roleRepository = roleRepository;
	}

	public User getUserById(long id) {
		return repository.findById(id).orElse(null);
	}

	public List<UserResponse> getAllUsers() {
		List<User> users = repository.findAll();
		List<UserResponse> userResponses = new ArrayList<UserResponse>();
		for(User user: users) {
			UserResponse response = new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles());
			userResponses.add(response);
		}
		return userResponses;
	}

	public void makeAdmin(long userId) {

		try {
			User user = repository.findById(userId).orElse(null);
			
			Set<Role> roles = user.getRoles();
			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(adminRole);
			user.setRoles(roles);
			repository.save(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteUserById(long id) {
		repository.deleteById(id);
	}
}
