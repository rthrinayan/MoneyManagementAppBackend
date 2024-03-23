package com.rs.money.management.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rs.money.management.api.payloads.DataAnalysisRequest;
import com.rs.money.management.api.payloads.DebitDto;
import com.rs.money.management.api.responses.ResponseApi;
import com.rs.money.management.api.services.DebitServices;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/debit")
public class DebitController {
	
	@Autowired
	private DebitServices debitService;
	
	
	@PostMapping("/create")
	public ResponseEntity<DebitDto> createDebit(@RequestParam(value = "userId") Long userId, @Valid @RequestBody DebitDto DebitDto){
		DebitDto creatDebitDto = debitService.createDebit(DebitDto,userId);
		return new ResponseEntity<>(creatDebitDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{transactionId}")
	public ResponseEntity<DebitDto> updateDebit(@Valid @RequestBody DebitDto DebitDto,@PathVariable("transactionId") Long transactionId){
		DebitDto updatedDebitDto = debitService.updateDebit(DebitDto, transactionId);
		return ResponseEntity.ok(updatedDebitDto);
	}
	
	@DeleteMapping("/{transactionId}/delete")
	public ResponseEntity<ResponseApi> deleteDebit(@PathVariable Long transactionId){
		this.debitService.deleteDebit(transactionId);
		return new ResponseEntity<>(new ResponseApi(transactionId+"Deleted Successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<List<DebitDto>> getAllDebits(@PathVariable("userId") Long userId){
		List<DebitDto> list = this.debitService.getAllDebits(userId);
		return ResponseEntity.ok(list);
		}
	
	@GetMapping("/{transactionId}")
	public ResponseEntity<DebitDto> getDebitById(@PathVariable Long transactionId){
		DebitDto DebitDto = this.debitService.getDebitById(transactionId);
		return ResponseEntity.ok(DebitDto);
		}
	
	@PostMapping("/weekData/{userId}")
	public ResponseEntity<List<DebitDto>> getWeeklyData(@PathVariable Long userId,@RequestBody DataAnalysisRequest dataAnalysisRequest){
		System.out.println("Hello");
		List<DebitDto> debitDtos = this.debitService.getWeeklyDebits(userId,dataAnalysisRequest.getDate());
		return ResponseEntity.ok(debitDtos);
	}
}
