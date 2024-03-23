package com.rs.money.management.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.money.management.api.entities.User;
import com.rs.money.management.api.enums.Roles;
import com.rs.money.management.api.jwt.JwtServices;
import com.rs.money.management.api.payloads.AuthenticationRequest;
import com.rs.money.management.api.repository.UserRepo;
import com.rs.money.management.api.responses.AuthenticationResponse;
import com.rs.money.management.api.responses.RegisterRequest;

import lombok.Data;

@Service
@Data
public class AuthenticationService {
	
	@Autowired
	private final UserRepo repository;
	
	@Autowired
	private final JwtServices jwtService;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;

	public AuthenticationResponse register(RegisterRequest request) {
		System.out.println(getClass());
		User user = User.builder()
					.fullName(request.getFullName())
					.email(request.getEmail())
					.password(new BCryptPasswordEncoder().encode(request.getPassword()))
					.roles(Roles.User)
					.build();
		repository.save(user);
		String jwtToken = jwtService.generateToken(user);
				return AuthenticationResponse.builder()
				.token(jwtToken)
				.userId(user.getUserId())
				.expirationDate(jwtService.extractExpiration(jwtToken))
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		System.out.println(getClass());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(),
							request.getPassword()
							)
						);
		User user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.userId(user.getUserId())
				.expirationDate(jwtService.extractExpiration(jwtToken))
				.build();
	}

}
