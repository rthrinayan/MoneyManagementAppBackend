package com.rs.money.management.api.responses;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ResponseApi {

	String messageString;
	boolean success;
	public ResponseApi(String messageString, boolean success) {
		super();
		this.messageString = messageString;
		this.success = success;
	}

 
}
