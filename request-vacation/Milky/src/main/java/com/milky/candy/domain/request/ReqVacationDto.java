package com.milky.candy.domain.request;

import java.util.Date;

import com.milky.candy.domain.response.ResVacationDto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ReqVacationDto {
	private Date startDate;
	private Date endDate;
	private String comment;
}
