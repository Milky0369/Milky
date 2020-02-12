package com.milky.candy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.milky.candy.domain.VacationHistory;

public interface VacationHistoryDbRepository extends JpaRepository<VacationHistory, Long> {

	@Query(value="SELECT "
			+ "SEQ, "
			+ "EMP_NUM, "
			+ "TIME_TYPE, "
			+ "START_DATE, "
			+ "END_DATE, "
			+ "CANCEL_FLAG, "
			+ "REG_DATE, "
			+ "UPT_DATE "
			+ "FROM VACATION_HISTORY "
			+ "WHERE EMP_NUM = :empNum "
			+ "      AND CANCEL_FLAG = 'N' "
			+ "      AND START_DATE = :startDate "
			+ "      AND END_DATE = :endDate ", nativeQuery=true)
	VacationHistory selectHistory(@Param("empNum") int empNum, @Param("startDate") String startDate, @Param("endDate") String endDate);

}
