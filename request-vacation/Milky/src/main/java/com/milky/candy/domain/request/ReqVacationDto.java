package com.milky.candy.domain.request;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ReqVacationDto {
	private String userId;
	private String vacationType;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String comment;
}
