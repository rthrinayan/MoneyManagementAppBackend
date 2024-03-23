package com.rs.money.management.api.services;

import java.util.Date;
import java.util.List;

import com.rs.money.management.api.payloads.DebitDto;


public interface DebitServices {
	
	DebitDto createDebit(DebitDto creditDto, Long userId);
	
	DebitDto updateDebit(DebitDto creditDto, Long transactionId);
	
	DebitDto getDebitById(Long transactionId);
	
	List<DebitDto> getAllDebits(Long userId);
	
	List<DebitDto> getWeeklyDebits(Long userId,Date date);
	
	void deleteDebit(Long transactionId);
}
