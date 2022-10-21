package com.nology.Jobs4;

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

import com.nology.Jobs4.Jobs.Job;
import com.nology.Jobs4.Jobs.JobDTO;
import com.nology.Jobs4.Temps.Temp;
import com.nology.Jobs4.Temps.TempDTO;

@RestController
public class WorkerController {
	
	@Autowired
	private WorkerService service; 
	
	@GetMapping(value = "/jobs")
	public List<Job> getJobs() {
		return service.allJobs(); 
	}
	
	@GetMapping(value = "/temps")
	public List<Temp> getTemps() {
		return service.allTemps();
	}
	
	@PostMapping(value = "/jobs")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void saveJob(@Valid @RequestBody JobDTO job) {
		service.createJob(job);
	}
	
	@PostMapping(value = "/temps")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void saveTemp(@Valid @RequestBody TempDTO temp) {
		service.createTemp(temp);
	}
	
	@GetMapping(value = "/jobs/{id}")
	public ResponseEntity<Job> findJob(@PathVariable Long id){
		Job job = this.service.findJob(id);
		if(job.equals(null)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(job, HttpStatus.OK);
	}
	
	@GetMapping(value = "/temps/{id}")
	public ResponseEntity<Temp> findTemp(@PathVariable Long id){
		Temp temp = this.service.findTemp(id);
		if(temp.equals(null)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@PatchMapping(value = "jobs/{id}")
	public ResponseEntity<Job> updateJob (@PathVariable Long id, @RequestBody JobDTO data){
		Job job = this.service.updateJobDetails(id, data);
		if(job == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(job, HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value = "temps/{id}")
	public ResponseEntity<Temp> updateTemp(@PathVariable Long id, @RequestBody TempDTO data){
		Temp temp = this.service.updateTempDetails(id, data);
		if(temp == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(temp, HttpStatus.NO_CONTENT);
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
	
	@DeleteMapping(value = "temps/{id}")
	public ResponseEntity<Temp> deleteTemp(@PathVariable Long id){
		Temp temp = this.service.findTemp(id);
		
		if(temp.equals(null)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		this.service.deleteTemp(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PutMapping(value = "jobs/{jobId}/temps/{tempId}")
	public ResponseEntity<Job> assignJob(@PathVariable Long jobId, @PathVariable Long tempId){
		Job job = this.service.assignJob(jobId, tempId); 
		if(job == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(job, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "jobs/assigned={boo}")
		public List<Job> checkJobs(@PathVariable Boolean boo) {
			return service.checkJob(boo); 
		}
	
	@GetMapping(value = "temps/jobId={jobId}")
		public List<Temp> checkTemps(@PathVariable Long jobId){
			return service.checkTemp(jobId); 
		}

}
