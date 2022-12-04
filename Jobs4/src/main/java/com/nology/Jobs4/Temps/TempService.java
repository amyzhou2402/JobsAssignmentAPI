package com.nology.Jobs4.Temps;

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
import com.nology.Jobs4.Jobs.JobRepository;

@Service
@Transactional
public class TempService {
	@Autowired
	private TempRepository tempRepository;
	private JobRepository jobRepository; 
	
	
	public List<Temp> allTemps() {
		return tempRepository.findAll(); 
	}
	
	public void createTemp(TempDTO temp) {
		Temp t= new Temp(temp.getFirstName(), temp.getLastName());
		tempRepository.save(t);
	}
	
	public Temp findTemp(Long id) {
		return this.tempRepository.findById(id).get();
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
	
	public void deleteTemp(Long id) {
		this.tempRepository.deleteById(id);
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
