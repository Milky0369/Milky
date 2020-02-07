CREATE TABLE USER (
	EMP_NUM 	INT(11)       NOT NULL 				 AUTO_INCREMENT COMMENT '사번 (자동 증가 값)',
	USER_ID		VARCHAR(50)   NOT NULL 				                COMMENT '사용자 아이디',
	USER_PWD	VARCHAR(100)  NOT NULL				                COMMENT '사용자 비밀번호',
	REG_DATE	DATETIME      NOT NULL  DEFAULT NOW()               COMMENT '데이터 등록 시간',
	UPT_DATE	DATETIME                DEFAULT NULL                COMMENT '데이터 갱신 시간',
	PRIMARY KEY (EMP_NUM)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='사용자 관리 테이블';

CREATE TABLE VACATION (
	EMP_NUM 	INT(11)       NOT NULL 				                COMMENT '사번 (ref.USER.EMP_NUM)',
	VACATION    DECIMAL(2,2)  NOT NULL 	DEFAULT '0.0'               COMMENT '휴가일수',
	REG_DATE	DATETIME      NOT NULL  DEFAULT NOW()               COMMENT '데이터 등록 시간',
	UPT_DATE	DATETIME                DEFAULT NULL                COMMENT '데이터 갱신 시간',
	PRIMARY KEY (EMP_NUM)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='휴가 관리 테이블';
