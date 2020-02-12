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
	VACATION    DECIMAL(4,2)  NOT NULL 	DEFAULT '0.0'               COMMENT '휴가일수',
	REG_DATE	DATETIME      NOT NULL  DEFAULT NOW()               COMMENT '데이터 등록 시간',
	UPT_DATE	DATETIME                DEFAULT NULL                COMMENT '데이터 갱신 시간',
	PRIMARY KEY (EMP_NUM)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='휴가 관리 테이블';

CREATE TABLE VACATION_HISTORY (
	SEQ         INT(11)       NOT NULL 				 AUTO_INCREMENT COMMENT '자동 증가 값',
	EMP_NUM 	INT(11)       NOT NULL 				                COMMENT '사번 (ref.USER.EMP_NUM)',
	TIME_TYPE   VARCHAR(20)         	DEFAULT NULL                COMMENT '휴가 구분',
	START_DATE	VARCHAR(10)             DEFAULT NULL                COMMENT '휴가 시작일',
	END_DATE	VARCHAR(10)             DEFAULT NULL                COMMENT '휴가 종료일',
	USE_FLAG    VARCHAR(1)           	DEFAULT 'N'                 COMMENT '휴가 사용 구분',
	CANCEL_FLAG VARCHAR(1)           	DEFAULT 'N'                 COMMENT '휴가 취소 구분',
	REG_DATE	DATETIME      NOT NULL  DEFAULT NOW()               COMMENT '데이터 등록 시간',
	UPT_DATE	DATETIME                DEFAULT NULL                COMMENT '데이터 갱신 시간',
	PRIMARY KEY (SEQ)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='휴가 히스토리 테이블';

-- batch
CREATE TABLE BAT_JOB_INSTANCE  (
	JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY COMMENT 'job instance 아이디',
	VERSION BIGINT COMMENT 'job instance 생성 버전_J',
	JOB_NAME VARCHAR(100) NOT NULL COMMENT 'job 이름_J',
	JOB_KEY VARCHAR(32) NOT NULL COMMENT 'job 고유키_J',
	constraint JOB_INST_UN_INTG unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BAT_JOB_EXECUTION  (
	JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY,
	VERSION BIGINT  COMMENT 'job execution 생성 버전_J',
	JOB_INSTANCE_ID BIGINT NOT NULL COMMENT 'job instance 아이디_J',
	CREATE_TIME DATETIME NOT NULL COMMENT 'job instance 생성시간_J',
	START_TIME DATETIME DEFAULT NULL COMMENT 'job instance 시작 시간_J',
	END_TIME DATETIME DEFAULT NULL COMMENT 'job instance 종료 시간_J',
	STATUS VARCHAR(10) COMMENT 'job instance 상태_J',
	EXIT_CODE VARCHAR(2500) COMMENT 'job instance 종료 코드_J',
	EXIT_MESSAGE VARCHAR(4000) COMMENT 'job instance 종료 메시지_J',
	LAST_UPDATED DATETIME COMMENT 'job instance 최종 변경 시간_J',
	JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL COMMENT 'job 설정 위치_J',
	constraint JOB_INST_EXEC_FK_INTG foreign key (JOB_INSTANCE_ID)
	references BAT_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BAT_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL COMMENT 'job execution 아이디_J',
	TYPE_CD VARCHAR(6) NOT NULL COMMENT '데이터 자료형_J',
	KEY_NAME VARCHAR(100) NOT NULL COMMENT '키 값 이름_J',
	STRING_VAL VARCHAR(250) COMMENT 'string value_J',
	DATE_VAL DATETIME DEFAULT NULL COMMENT 'date value_J',
	LONG_VAL BIGINT COMMENT 'long value_J',
	DOUBLE_VAL DOUBLE PRECISION COMMENT 'double value_J',
	IDENTIFYING CHAR(1) NOT NULL COMMENT 'job instance 관련 파라미터 여부_J',
	constraint JOB_EXEC_PARAMS_FK_INTG foreign key (JOB_EXECUTION_ID)
	references BAT_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE `BAT_STEP_EXECUTION` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL COMMENT 'job execution step 생성 버전_J',
  `STEP_NAME` varchar(100) NOT NULL COMMENT 'job step 이름_J',
  `JOB_EXECUTION_ID` bigint(20) NOT NULL COMMENT 'job execution 아이디_J',
  `START_TIME` datetime NOT NULL COMMENT 'step 시작 시간_J',
  `END_TIME` datetime DEFAULT NULL COMMENT 'step 종료 시간_J',
  `STATUS` varchar(10) DEFAULT NULL COMMENT 'step 상태_J',
  `COMMIT_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 커밋 수행 횟수_J',
  `READ_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 읽은 횟수_J',
  `FILTER_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 필터링 수_J',
  `WRITE_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 쓴 횟수_J',
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 읽음 스킵 횟수_J',
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 씀 스킵 횟수_J',
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 프로세스 스킵 횟수_J',
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL COMMENT 'step 롤백 횟수_J',
  `EXIT_CODE` varchar(2500) DEFAULT NULL COMMENT 'step 종료 코드_J',
  `EXIT_MESSAGE` varchar(3000) DEFAULT NULL COMMENT 'step 종료 메시지_J',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT 'step 최종 변경 시간_J',
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `IDX_STEP_EXECUTION` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK_INTG` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BAT_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB;

CREATE TABLE BAT_STEP_EXECUTION_CONTEXT  (
	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY COMMENT 'step execution 아이디_J',
	SHORT_CONTEXT VARCHAR(2500) NOT NULL COMMENT 'step context_J',
	SERIALIZED_CONTEXT TEXT  COMMENT 'job execution step 직렬화 context_J',
	constraint STEP_EXEC_CTX_FK_INTG foreign key (STEP_EXECUTION_ID)
	references BAT_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BAT_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY COMMENT 'job execution 아이디_J',
	SHORT_CONTEXT VARCHAR(2500) NOT NULL COMMENT 'job execution context_J',
	SERIALIZED_CONTEXT TEXT COMMENT 'job execution 직렬화 context_J',
	constraint JOB_EXEC_CTX_FK_INTG foreign key (JOB_EXECUTION_ID)
	references BAT_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BAT_STEP_EXECUTION_SEQ (
	ID BIGINT NOT NULL COMMENT 'step_execution 아이디',
	UNIQUE_KEY CHAR(1) NOT NULL  COMMENT '유니크 키',
	constraint UNIQUE_KEY_UN_INTG unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BAT_STEP_EXECUTION_SEQ values(0, '0');

CREATE TABLE BAT_JOB_EXECUTION_SEQ (
	ID BIGINT NOT NULL COMMENT 'job_execution 아이디',
	UNIQUE_KEY CHAR(1) NOT NULL COMMENT '유니크 키',
	constraint UNIQUE_KEY_UN_INTG unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BAT_JOB_EXECUTION_SEQ values(0, '0');

CREATE TABLE BAT_JOB_SEQ (
	ID BIGINT NOT NULL COMMENT 'Batch Job 아이디',
	UNIQUE_KEY CHAR(1) NOT NULL COMMENT '유니크 키',
	constraint UNIQUE_KEY_UN_INTG unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BAT_JOB_SEQ values(0, '0');


-- OAuth
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove tinyint
);

create table oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(255), authentication BLOB
);