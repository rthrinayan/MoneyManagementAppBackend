package com.rs.money.management.api.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Credits {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	
	 private float cost;
	 
	 private int quantity;
	 
	 private Date createdDate;
	 
	 private String productName;
	 
	 private double longitude;
		
	 private double latitude;
		
	 private String address;
	 
	 @ManyToOne
	 private User user;
}
