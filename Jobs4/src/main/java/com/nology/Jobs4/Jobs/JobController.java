package com.nology.Jobs4.Jobs;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
	
	@Autowired
	private JobService service;
	
	@GetMapping(value = "/jobs")
	public List<Job> getJobs() {
		return service.allJobs(); 
	}
	
	@PostMapping(value = "/jobs")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Job saveJob(@Valid @RequestBody JobDTO jobData) {
		service.createJob(jobData);
		return null;
	}
	
	@GetMapping(value = "/jobs/{id}")
	public ResponseEntity<Job> findJob(@PathVariable Long id){
		Job job = this.service.findJob(id);
		if(job.equals(null)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(job, HttpStatus.OK);
	}
	
	@PatchMapping(value = "jobs/{id}")
	public ResponseEntity<Job> updateJob (@PathVariable Long id, @RequestBody JobDTO jobData){
		Job job = this.service.updateJobDetails(id, jobData);
		if(job == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(job, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "jobs/{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable Long id){
		Job job = this.service.findJob(id);
		
		if(job.equals(null)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		this.service.deleteJob(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping(value = "jobs/assigned={boo}")
	public List<Job> checkJobs(@PathVariable Boolean boo) {
		return service.checkJob(boo); 
	}
	
	@PutMapping(value = "jobs/{jobId}/temps/{tempId}")
	public ResponseEntity<Job> assignJob(@PathVariable Long jobId, @PathVariable Long tempId){
		Job job = this.service.assignJob(jobId, tempId); 
		if(job == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(job, HttpStatus.NO_CONTENT);
	}

}
