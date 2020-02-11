package com.milky.candy.domain.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ResVacationDto {
	private double useVacation;
	private double remainingVacation;
	
	// ResultCode enum 객체화 -> HTTP response status code format
	private String resultCode;
}
