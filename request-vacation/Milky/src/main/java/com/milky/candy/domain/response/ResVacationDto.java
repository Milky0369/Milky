package com.milky.candy.domain.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ResVacationDto {
	private double useVacation;
	private double remainingVacation;
	
	private String resultCode;
	private String msg;
}
