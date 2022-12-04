package com.nology.Jobs4.Jobs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nology.Jobs4.Temps.Temp;
import com.nology.Jobs4.Temps.TempService;

@Service
@Transactional
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	private TempService tempService; 
	
	public List<Job> allJobs() {
		return jobRepository.findAll(); 
	}
	
	public void createJob(JobDTO job) {
		Job j = new Job(job.getName(), job.getStartDate(), job.getEndDate());
		jobRepository.save(j); 
		
	}
	
	public Job findJob(Long id){
		return this.jobRepository.findById(id).get();
	}

	public Job updateJobDetails(Long id, JobDTO data) {
		Job fetchedJob = this.findJob(id);
		if(fetchedJob.equals(null)) {
			return null; 
		}
				
		if(data.getName() != null && !data.getName().equals("")) {
			fetchedJob.setName(data.getName());
		}
		
		if(data.getStartDate() != null) {
			fetchedJob.setStartDate(data.getStartDate());
		}
		
		if(data.getEndDate() != null) {
			fetchedJob.setEndDate(data.getEndDate());
		}
		
		return this.jobRepository.save(fetchedJob); 
	}
	
	public void deleteJob(Long id) {
		this.jobRepository.deleteById(id);
	}
	
	//assignJob
	public Job assignJob(Long jobId, Long tempId) {
		Job fetchedJob = this.findJob(jobId);
		Temp fetchedTemp = tempService.findTemp(tempId);
		
		if(fetchedJob.equals(null) || fetchedTemp.equals(null)) {
			return null; 
		}
		
		Job existingJob = fetchedJob; 
		Temp existingTemp = fetchedTemp;
		
		existingJob.setTemp(existingTemp);
		return this.jobRepository.save(existingJob);
	}
	
	//check if job is assigned to temp or not
		public List<Job> checkJob(Boolean boo) {
			List<Job> allJobs = allJobs();
			List<Job> filteredList = new ArrayList<>();
			for (Job job: allJobs) {
				if( boo == true) {
					if (job.findTemp() != null) {
						filteredList.add(job);
					}
				}
				if(boo == false) {
					if (job.findTemp() == null) {
						filteredList.add(job);
					}
				}
			}
			return filteredList;
		}
	
	
}
