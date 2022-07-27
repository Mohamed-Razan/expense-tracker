package com.insight.cdp.serviceInterface;

import java.util.List;

import com.insight.cdp.model.User;
import com.insight.cdp.payload.response.UserResponse;

public interface UserServiceInterface {
	public User getUserById(long id);
	public List<UserResponse> getAllUsers();
	public void makeAdmin(long userId);
	public void deleteUserById(long id);
}
