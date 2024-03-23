package com.rs.money.management.api.services;

import java.util.Date;
import java.util.List;

import com.rs.money.management.api.payloads.CreditDto;


public interface CreditServices {
	
	CreditDto createCredit(CreditDto creditDto, Long userId);
	
	CreditDto updateCredit(CreditDto creditDto, Long transactionId);
	
	CreditDto getCreditById(Long transactionId);
	
	List<CreditDto> getAllCredits(Long userId);
	
	List<CreditDto> getAllWeekCredit(Long userId, Date date);
	
	void deleteCredit(Long transactionId);
}
