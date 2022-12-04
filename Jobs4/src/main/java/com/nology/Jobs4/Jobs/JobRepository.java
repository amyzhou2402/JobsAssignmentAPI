package com.nology.Jobs4.Jobs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>{
	// add sql query here for getAllJobs
	// justify using sql in the repo instead of service bc less scalable if you’re looping through all the jobs 
	// (big O problem). mysql might do something similar but its more optimised


}
