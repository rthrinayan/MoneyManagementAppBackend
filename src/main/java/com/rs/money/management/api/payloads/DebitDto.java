package com.rs.money.management.api.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebitDto {
	
	private long transactionId;
	
	private Date createdDate;
	
	private float amount;
}
