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

import com.rs.money.management.api.payloads.CreditDto;
import com.rs.money.management.api.payloads.DataAnalysisRequest;
import com.rs.money.management.api.responses.ResponseApi;
import com.rs.money.management.api.services.CreditServices;

import jakarta.validation.Valid;

@Controller
@RequestMapping("api/credits")
public class CreditController {
	
	@Autowired
	private CreditServices creditService;
	
	
	@PostMapping("/create")
	public ResponseEntity<CreditDto> createCredit(@RequestParam(value = "userId") Long userId, @Valid @RequestBody CreditDto CreditDto){
		CreditDto creatCreditDto = creditService.createCredit(CreditDto,userId);
		return new ResponseEntity<>(creatCreditDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{transactionId}")
	public ResponseEntity<CreditDto> updateCredit(@Valid @RequestBody CreditDto CreditDto,@PathVariable("transactionId") Long transactionId){
		CreditDto updatedCreditDto = creditService.updateCredit(CreditDto, transactionId);
		return ResponseEntity.ok(updatedCreditDto);
	}
	
	@DeleteMapping("/{transactionId}/delete")
	public ResponseEntity<ResponseApi> deleteCredit(@PathVariable Long transactionId){
		this.creditService.deleteCredit(transactionId);
		return new ResponseEntity<>(new ResponseApi(transactionId+"Deleted Successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<List<CreditDto>> getAllCredits(@PathVariable("userId") Long userId){
		List<CreditDto> list = this.creditService.getAllCredits(userId);
		return ResponseEntity.ok(list);
		}
	
	@GetMapping("/{transactionId}")
	public ResponseEntity<CreditDto> getCreditById(@PathVariable Long transactionId){
		CreditDto CreditDto = this.creditService.getCreditById(transactionId);
		return ResponseEntity.ok(CreditDto);
		}
	
	@PostMapping("/weekData/{userId}")
	public ResponseEntity<List<CreditDto>> getDate(@PathVariable Long userId, @RequestBody DataAnalysisRequest dataAnalysisRequest){
		List<CreditDto> creditDtos = this.creditService.getAllWeekCredit(userId,dataAnalysisRequest.getDate());
		return ResponseEntity.ok(creditDtos);
	}
	
}
