package com.rs.money.management.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rs.money.management.api.payloads.UserDto;
import com.rs.money.management.api.responses.ResponseApi;
import com.rs.money.management.api.services.UserServices;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	private UserServices userService;
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Long uid){
		UserDto updatedUserDto = userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUserDto);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseApi> deleteUser(@PathVariable Long userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<>(new ResponseApi(userId+"Deleted Successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> list = this.userService.getAllUser();
		return ResponseEntity.ok(list);
		}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
		UserDto userDto = this.userService.getUserById(userId);
		return ResponseEntity.ok(userDto);
		}
}
