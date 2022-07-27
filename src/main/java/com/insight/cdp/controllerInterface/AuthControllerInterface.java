package com.insight.cdp.controllerInterface;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.insight.cdp.payload.request.LoginRequest;
import com.insight.cdp.payload.request.SignupRequest;

public interface AuthControllerInterface {

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

	public ResponseEntity<?> registerUser(SignupRequest signUpRequest, HttpServletRequest request) throws UnsupportedEncodingException;
	
}

