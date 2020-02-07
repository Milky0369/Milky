package com.milky.candy.domain.request;

import java.util.Date;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ReqLoginDto {
	private String userId;
	private String userPwd;
}
