package com.rs.money.management.api.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
	
	private String fullName;
	private String email;
	private String password;
}
