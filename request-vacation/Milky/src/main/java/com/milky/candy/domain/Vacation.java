package com.milky.candy.domain;

import java.util.Date;

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
	private float vacation;
	private Date regDate;
	private Date uptDate;
}
