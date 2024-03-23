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
public class CreditDto {
	
	private long transactionId;
	
	private float cost;
	
	private int quantity;
	
	private Date createdDate;
	
	private String productName;
	
	private double longitude;
	
	private double latitude;
	
	private String address;

}
