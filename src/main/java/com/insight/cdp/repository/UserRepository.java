package com.insight.cdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insight.cdp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByResetPasswordToken(String token);
	
	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
	Optional<User> findByVerificationCode(String verificationCode);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}