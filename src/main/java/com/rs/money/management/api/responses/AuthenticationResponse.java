package com.rs.money.management.api.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
	
	//This will be sent back to the user
	private String token;
	
	private Long userId;
	
	private Date expirationDate;
	
}
