package com.milky.candy.service;

import com.milky.candy.domain.request.ReqVacationDto;
import com.milky.candy.domain.response.ResVacationDto;

public interface VacationService {
	ResVacationDto useVacation(ReqVacationDto request);
	ResVacationDto cancelVacation(ReqVacationDto request);
}
