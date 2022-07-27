package com.insight.cdp.payload.response;

import java.util.Set;

import com.insight.cdp.model.Role;

public class UserResponse {

	private long id;
	private String username;
	private String email;
	private Set<Role> roles;

	public UserResponse(long id, String username, String email, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public UserResponse() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
