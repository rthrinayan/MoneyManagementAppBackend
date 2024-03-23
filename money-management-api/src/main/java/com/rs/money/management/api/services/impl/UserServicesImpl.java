package com.rs.money.management.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.money.management.api.entities.User;
import com.rs.money.management.api.exceptions.ResourceNotFoundException;
import com.rs.money.management.api.payloads.UserDto;
import com.rs.money.management.api.repository.UserRepo;
import com.rs.money.management.api.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User updatedUser = this.userRepo.save(user);
		return this.entityToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Long userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.entityToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users= this.userRepo.findAll();
		List<UserDto> usersDtos = users.stream().map(user->this.entityToUserDto(user)).collect(Collectors.toList());
		
		return usersDtos;
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepo.deleteById(userId);

	}
	
	private UserDto entityToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
