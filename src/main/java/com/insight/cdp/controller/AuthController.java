package com.insight.cdp.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insight.cdp.controllerInterface.AuthControllerInterface;
import com.insight.cdp.payload.request.LoginRequest;
import com.insight.cdp.payload.request.SignupRequest;
import com.insight.cdp.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerInterface {
	
	private AuthService service;
	
	public AuthController(AuthService authService) {
		this.service = authService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return service.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws UnsupportedEncodingException {

		return service.registerUser(signUpRequest);
	}
	
}

