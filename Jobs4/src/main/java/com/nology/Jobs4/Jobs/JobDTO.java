package com.nology.Jobs4.Jobs;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.nology.Jobs4.Temps.Temp;

public class JobDTO {
	
//	private Job job; 
	
	@NotNull
	private String name; 
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	public Temp temp; 

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

	public Temp getTemp() {
		return temp;
	}

	public void setTemp(Temp temp) {
		this.temp = temp;
	} 
	

}
