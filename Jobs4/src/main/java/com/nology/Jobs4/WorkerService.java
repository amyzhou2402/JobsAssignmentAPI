package com.nology.Jobs4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nology.Jobs4.Jobs.Job;
import com.nology.Jobs4.Jobs.JobDTO;
import com.nology.Jobs4.Jobs.JobRepository;
import com.nology.Jobs4.Temps.Temp;
import com.nology.Jobs4.Temps.TempDTO;
import com.nology.Jobs4.Temps.TempRepository;

@Service
@Transactional
public class WorkerService {
	
	@Autowired
	private JobRepository jobRepository; 
	
	@Autowired
	private TempRepository tempRepository;
	
	public List<Job> allJobs() {
		return jobRepository.findAll(); 
	}
	
	public List<Temp> allTemps() {
		return tempRepository.findAll(); 
	}
	
	public void createJob(JobDTO job) {
		Job j = new Job(job.getName(), job.getStartDate(), job.getEndDate());
		jobRepository.save(j); 
		
	}
	
	public void createTemp(TempDTO temp) {
		Temp t= new Temp(temp.getFirstName(), temp.getLastName());
		tempRepository.save(t);
	}
	
	public Job findJob(Long id){
		return this.jobRepository.findById(id).get();
	}
	
	public Temp findTemp(Long id) {
		return this.tempRepository.findById(id).get();
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
	
	public Temp updateTempDetails(Long id, TempDTO data) {
		Temp fetchedTemp = this.findTemp(id);
		
		if(fetchedTemp.equals(null)) {
			return null; 
		}
		
		if(data.getFirstName() != null && !data.getFirstName().equals("")){
			fetchedTemp.setFirstName(data.getFirstName());
		}
		
		if(data.getLastName() != null && !data.getLastName().equals("")){
			fetchedTemp.setLastName(data.getLastName());
		}
		
		return this.tempRepository.save(fetchedTemp);
	
	}
	
	public void deleteJob(Long id) {
		this.jobRepository.deleteById(id);
	}

	public void deleteTemp(Long id) {
		this.tempRepository.deleteById(id);
	}
	
	//assignJob
	public Job assignJob(Long jobId, Long tempId) {
		Job fetchedJob = this.findJob(jobId);
		Temp fetchedTemp = this.findTemp(tempId);
		
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
	
	//  get temps that are 
	// (1) not assigned and 
	// (2) available in job range
	
	public List<Temp> checkTemp(Long jobId){
		Optional<Job> fetchedJob = jobRepository.findById(jobId);

		if (fetchedJob.isEmpty())
			return null;

		Job currentJob = jobRepository.findById(jobId).get();

		ArrayList<Temp> tempList = new ArrayList<>();

		for (Temp temp : allTemps()) {

//			zero check
			if (temp.getJobsArr().size() == 0) {
				tempList.add(temp);
				continue;
			}
			LocalDate currentStart, currentFinish, newStart, newFinish;
			currentStart = currentJob.getStartDate();
			currentFinish = currentJob.getEndDate();

//			sorted jobArr
			ArrayList<Job> jobArr = new ArrayList<>();
			for (Job job : temp.getJobsArr()) {
				jobArr.add(job);
				continue;
			}
			Collections.sort(jobArr, new Comparator<Job>() {
				public int compare(Job a, Job b) {
					return a.getStartDate().compareTo(b.getStartDate());
				}
			});

//			start and end checks
			newStart = jobArr.get(0).getStartDate();
			newFinish = jobArr.get(jobArr.size() - 1).getEndDate();
			if (currentFinish.isBefore(newStart)) {
				tempList.add(temp);
				continue;
			}
			if (newFinish.isBefore(currentStart)) {
				tempList.add(temp);
				continue;
			}

//			in between checks
			for (int i = 0; i < jobArr.size() - 1; i++) {
				newFinish = jobArr.get(i).getEndDate();
				newStart = jobArr.get(i + 1).getStartDate();
				if (newFinish.isBefore(currentStart) && currentFinish.isBefore(newStart)) {
					tempList.add(temp);
					continue;
				}
			}
		}
		return tempList;		
	}
	
	
	
	
	
	
}
