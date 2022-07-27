package com.insight.cdp.controllerInterface;

import java.util.List;

import com.insight.cdp.payload.response.UserResponse;

public interface UserControllerInterface {

	public List<UserResponse> getAllUsers();
	public void makeAdmin(long userId);
	public void deleteUserById(long id);
}
