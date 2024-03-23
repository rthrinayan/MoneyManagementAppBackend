package com.rs.money.management.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	 String fieldName;
	 long fieldValue;
	 
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	 
}
