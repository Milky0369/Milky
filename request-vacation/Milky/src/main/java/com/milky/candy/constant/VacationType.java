package com.milky.candy.constant;

public enum VacationType {
	HALF_DAY_OFF_AM("HALF_DAY_OFF_AM", 0.5),
	HALF_DAY_OFF_PM("HALF_DAY_OFF_PM", 0.5),
	HALF_HALF_DAY_OFF_AM("HALF_HALF_DAY_OFF_AM", 0.25),
	HALF_HALF_DAY_OFF_PM("HALF_HALF_DAY_OFF_PM", 0.25),
	DAY_OFF("DAY_OFF", 1.0);
	
	String vacationType;
	double vacationDay;
	
	VacationType(String _vacationType, double _vacationDay) {
		this.vacationType = _vacationType;
		this.vacationDay = _vacationDay;
	}
	
	public String getVacationType() {
		return this.vacationType;
	}
	
	public double getVacationDay() {
		return this.vacationDay;
	}
	
	public static VacationType find(String _vacationType) {
		if (_vacationType == null)
			return null;
		
		for (VacationType code : values()) {
			if (code.getVacationType().equals(_vacationType))
				return code;
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		String type = "DAY_OFF";
//		
//		if(VacationType.DAY_OFF.getVacationType().equals(type)) {
//			System.out.println("test");
//		}
//		
//		System.out.println(VacationType.DAY_OFF.getVacationDay());
//		System.out.println(VacationType.DAY_OFF.getVacationType());
//	}
}
