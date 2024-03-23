package com.rs.money.management.api.payloads;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataAnalysisRequest {
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date date;
}
