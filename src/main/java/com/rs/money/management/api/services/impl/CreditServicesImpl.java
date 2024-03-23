package com.rs.money.management.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.money.management.api.entities.Credits;
import com.rs.money.management.api.entities.User;
import com.rs.money.management.api.exceptions.ResourceNotFoundException;
import com.rs.money.management.api.payloads.CreditDto;
import com.rs.money.management.api.repository.CreditRepo;
import com.rs.money.management.api.repository.UserRepo;
import com.rs.money.management.api.services.CreditServices;

@Service
public class CreditServicesImpl implements CreditServices {

	@Autowired
	private CreditRepo creditRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CreditDto createCredit(CreditDto CreditDto,Long userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		Credits Credit = this.CreditDtoToEntity(CreditDto);
		Credit.setCreatedDate(new Date(System.currentTimeMillis()));
		Credit.setUser(user);
		Credits savedCredit = creditRepo.save(Credit);
		
		return this.entityToCreditDto(savedCredit);
	}

	@Override
	public CreditDto updateCredit(CreditDto CreditDto, Long transactionId) {
		
		Credits Credit = this.creditRepo.findById(transactionId).orElseThrow(()-> new ResourceNotFoundException("Credit","Id",transactionId));
		
		Credit.setCost(CreditDto.getCost());
		Credit.setProductName(CreditDto.getProductName());
		Credit.setQuantity(CreditDto.getQuantity());
		Credits updatedCredit = this.creditRepo.save(Credit);
		
		return this.entityToCreditDto(updatedCredit);
	}

	@Override
	public CreditDto getCreditById(Long transactionId) {
		
		Credits Credit = this.creditRepo.findById(transactionId).orElseThrow(()-> new ResourceNotFoundException("Credit","Id",transactionId));
		return this.entityToCreditDto(Credit);
	}

	@Override
	public List<CreditDto> getAllCredits(Long userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Credits> Credits= this.creditRepo.findByUser(user);
		List<CreditDto> CreditsDtos = Credits.stream().map(Credit->this.entityToCreditDto(Credit)).collect(Collectors.toList());
		
		return CreditsDtos;
	}

	@Override
	public void deleteCredit(Long transactionId) {
		this.creditRepo.deleteById(transactionId);

	}
	
	@Override
	public List<CreditDto> getAllWeekCredit(Long userId, Date date) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Credits> credits = this.creditRepo.getWeeklyCredit(user,date);
		List<CreditDto> creditDtos = credits.stream().map((Credit)-> this.entityToCreditDto(Credit)).toList();
		System.out.println(creditDtos);
		return creditDtos;
	}

	
	private Credits CreditDtoToEntity(CreditDto CreditDto) {
		 Credits Credit=this.modelMapper.map(CreditDto, Credits.class);
		return Credit;
	}
	
	private CreditDto entityToCreditDto(Credits Credit) {
		CreditDto CreditDto = this.modelMapper.map(Credit, CreditDto.class);
		return CreditDto;
	}

	
}
