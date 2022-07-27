package com.insight.cdp.serviceInterface;

import java.io.UnsupportedEncodingException;
import org.springframework.http.ResponseEntity;
import com.insight.cdp.payload.request.LoginRequest;
import com.insight.cdp.payload.request.SignupRequest;

public interface AuthServiceInterface {

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
	

	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) throws UnsupportedEncodingException;
	
}
