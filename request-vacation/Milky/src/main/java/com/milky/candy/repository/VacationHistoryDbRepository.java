package com.milky.candy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milky.candy.domain.VacationHistory;

public interface VacationHistoryDbRepository extends JpaRepository<VacationHistory, Long> {

	VacationHistory findByEmpNumAndCancelFlag(int empNum, String cancelFlag);

}
