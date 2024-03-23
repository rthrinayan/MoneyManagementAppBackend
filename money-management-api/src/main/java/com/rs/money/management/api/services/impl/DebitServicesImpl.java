package com.rs.money.management.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.money.management.api.entities.Debits;
import com.rs.money.management.api.entities.User;
import com.rs.money.management.api.exceptions.ResourceNotFoundException;
import com.rs.money.management.api.payloads.DebitDto;
import com.rs.money.management.api.repository.DebitRepo;
import com.rs.money.management.api.repository.UserRepo;
import com.rs.money.management.api.services.DebitServices;


@Service
public class DebitServicesImpl implements DebitServices {

	@Autowired
	private DebitRepo debitRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DebitDto createDebit(DebitDto DebitDto,Long userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		Debits debit = this.DebitDtoToEntity(DebitDto);
		debit.setCreatedDate(new Date(System.currentTimeMillis()));
		debit.setUser(user);
		Debits savedDebit = debitRepo.save(debit);
		
		return this.entityToDebitDto(savedDebit);
	}

	@Override
	public DebitDto updateDebit(DebitDto DebitDto, Long transactionId) {
		
		Debits debit = this.debitRepo.findById(transactionId).orElseThrow(()-> new ResourceNotFoundException("Debit","Id",transactionId));
		
		debit.setAmount(DebitDto.getAmount());
		
		Debits updatedDebit = this.debitRepo.save(debit);
		
		return this.entityToDebitDto(updatedDebit);
	}

	@Override
	public DebitDto getDebitById(Long transactionId) {
		
		Debits Debit = this.debitRepo.findById(transactionId).orElseThrow(()-> new ResourceNotFoundException("Debit","Id",transactionId));
		return this.entityToDebitDto(Debit);
	}

	@Override
	public List<DebitDto> getAllDebits(Long userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Debits> Debits= this.debitRepo.findByUser(user);
		List<DebitDto> DebitsDtos = Debits.stream().map(Debit->this.entityToDebitDto(Debit)).collect(Collectors.toList());
		
		return DebitsDtos;
	}

	@Override
	public void deleteDebit(Long transactionId) {
		this.debitRepo.deleteById(transactionId);

	}
	
	@Override
	public List<DebitDto> getWeeklyDebits(Long userId, Date date) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Debits> debits = this.debitRepo.getWeeklyData(user, date);
		List<DebitDto> debitDtos = debits.stream().map((debit)-> this.entityToDebitDto(debit)).toList();
		return debitDtos;
	}
	
	private Debits DebitDtoToEntity(DebitDto DebitDto) {
		 Debits Debit=this.modelMapper.map(DebitDto, Debits.class);
		return Debit;
	}
	
	private DebitDto entityToDebitDto(Debits Debit) {
		DebitDto DebitDto = this.modelMapper.map(Debit, DebitDto.class);
		return DebitDto;
	}

}
