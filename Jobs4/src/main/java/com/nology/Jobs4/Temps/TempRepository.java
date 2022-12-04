package com.nology.Jobs4.Temps;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<Temp, Long> {
	// add sql query here for getAllTemps
	// justify using sql in the repo instead of service bc less scalable if you’re looping through all the jobs 
	// (big O problem). mysql might do something similar but its more optimised
}
