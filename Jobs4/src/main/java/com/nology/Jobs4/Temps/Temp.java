package com.nology.Jobs4.Temps;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.nology.Jobs4.Jobs.Job;

@Entity
public class Temp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;

	public Temp(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@OneToMany(mappedBy = "temp", cascade = CascadeType.ALL)
	private List<Job> jobsArr;

	public Temp() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Job> getJobsArr() {
		return jobsArr;
	}

	public void setJobsArr(List<Job> jobsArr) {
		this.jobsArr = jobsArr;
	}
}
