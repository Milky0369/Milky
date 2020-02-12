package com.milky.candy.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "VACATION_HISTORY")
public class VacationHistory {
	@Id
	private int seq;
	private int empNum;
	private String timeType;
	private String startDate;
	private String endDate;
	private String useFlag;
	private String cancelFlag;
	private LocalDateTime regDate;
	private LocalDateTime uptDate;
}
