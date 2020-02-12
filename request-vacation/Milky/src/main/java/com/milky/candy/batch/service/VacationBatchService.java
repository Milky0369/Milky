package com.milky.candy.batch.service;

import java.util.List;

public interface VacationBatchService<L> {
	
	List<L> bulkVacationList(List<L> list);

    L insertVacationList(L vacation);
    
}
