package com.milky.candy.service;

import com.milky.candy.domain.Vacation;
import com.milky.candy.domain.request.ReqVacationDto;

public interface VacationService {
	Vacation cunsumeVacation(ReqVacationDto request);
}
