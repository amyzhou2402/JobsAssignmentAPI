package com.nology.Jobs4.Jobs;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

public class JobDTO {
	
	
	@NotNull
	private String name; 
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	@FutureOrPresent
	private LocalDate endDate;
	
	public Long tempId; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Long getTemp() {
		return tempId;
	}

	public void setTemp(Long tempId) {
		this.tempId= tempId;
	} 
	

}
