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
@Table(name = "VACATION")
public class Vacation {
	@Id
	private int empNum;
	private double vacation;
	private LocalDateTime regDate;
	private LocalDateTime uptDate;
	
}
