package com.milky.candy.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int empNum;
	private String userId;
	private String userPwd;
	private Date regDate;
	private Date uptDate;
}
