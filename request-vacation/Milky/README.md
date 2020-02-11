request-vacation api
====================

> 크로키닷컴 개발 과제 (request-vacation api 제작)

개발 환경
--------------

```sh
- JAVA 8
- spring boot 2.1.8
  spring boot batch
  spring boot web
  spring boot batch
  spring boot jpa
```

JSON SAMPLE
--------------
1. 로그인
```sh
URL            : https://XXX.XXX.XXX.XXX:XXXX/milky/login
Content-Type   : application/json
Accept-Charset : UTF-8
HTTP protocal  : POST

json parameter :
	{
		"userId":"milky",
		"userPwd":"test"
	}
```
2. 연차 신청
```sh
URL            : https://XXX.XXX.XXX.XXX:XXXX/milky/vacation
Content-Type   : application/json
Accept-Charset : UTF-8
HTTP protocal  : POST

json parameter :
	{
		"userId":"milky",
		"vacationType":"DAY_OFF",
		"startDate":"2020-01-22T00:00:00.000",
		"endDate":"2020-01-29T00:00:00.000",
		"comment":"test"
	}
```
3. 반차 신청
```sh
URL            : https://XXX.XXX.XXX.XXX:XXXX/milky/vacation
Content-Type   : application/json
Accept-Charset : UTF-8
HTTP protocal  : POST

json parameter :
	{
		"userId":"milky",
		"vacationType":"HALF_DAY_OFF_AM",
		"startDate":"2020-01-22T00:00:00.000",
		"endDate":"2020-01-22T00:00:00.000",
		"comment":"test"
	}
```
4. 반반차 신청
```sh
URL            : https://XXX.XXX.XXX.XXX:XXXX/milky/vacation
Content-Type   : application/json
Accept-Charset : UTF-8
HTTP protocal  : POST

json parameter :
	{
		"userId":"milky",
		"vacationType":"HALF_HALF_DAY_OFF_AM",
		"startDate":"2020-01-22T00:00:00.000",
		"endDate":"2020-01-22T00:00:00.000",
		"comment":"test"
	}
```
5. 연차 취소
```sh
URL            : https://XXX.XXX.XXX.XXX:XXXX/milky/login
Content-Type   : application/json
Accept-Charset : UTF-8
HTTP protocal  : POST

json parameter :
	{
		"userId":"milky",
		"userPwd":"test"
	}
```

연차 사용 시나리오
---------
```sh
1. 연차 사용 시나리오 1 : 평일간 사용
   -

2. 연차 사용 시나리오 2 : 주말 포함 사용
   -

3. 연차 사용 시나리오 3 : 공휴일 포함 사용
   -

4. 연차 사용 시나리오 4 : 마지막일 잘못 입력
   -

5. 연차 사용 시나리오 5 : 연차 구분 잘못 입력
   -

6. 연차 사용 시나리오 6 : 파라미터 null 값 입력
   -
```

반차 사용 시나리오
---------
```sh
1. 반차 사용 시나리오 1 : 평일 오전 / 오후 반차 사용
   -

2. 반차 사용 시나리오 2 : 주말 오전 / 오후 반차 사용
   -

3. 반차 사용 시나리오 3 : 공휴일 오전 / 오후 반차 사용
   -

4. 반차 사용 시나리오 4 : 반차 구분 잘못 입력
   -

5. 반차 사용 시나리오 5 : 파라미터 null 값 입력
   -
```

반반차 사용 시나리오
---------
```sh
1. 반반차 사용 시나리오 1 : 평일 오전 / 오후 반반차 사용
   -

2. 반반차 사용 시나리오 2 : 주말 오전 / 오후 반반차 사용
   -

3. 반반차 사용 시나리오 3 : 공휴일 오전 / 오후 반반차 사용
   -

4. 반반차 사용 시나리오 4 : 반반차 구분 잘못 입력
   -

5. 반반차 사용 시나리오 5 : 파라미터 null 값 입력
   -
```
