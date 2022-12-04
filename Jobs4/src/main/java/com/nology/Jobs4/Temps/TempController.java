package com.nology.Jobs4.Temps;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {
	
	@Autowired
	private TempService service; 
	
	@GetMapping(value = "/temps")
	public List<Temp> getTemps() {
		return service.allTemps();
	}
	
	@PostMapping(value = "/temps")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Temp saveTemp(@Valid @RequestBody TempDTO tempData) {
		service.createTemp(tempData);
		return null;
	}
		
	@GetMapping(value = "/temps/{id}")
	public ResponseEntity<Temp> findTemp(@PathVariable Long id){
		Temp temp = this.service.findTemp(id);
		if(temp.equals(null)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@PatchMapping(value = "temps/{id}")
	public ResponseEntity<Temp> updateTemp(@PathVariable Long id, @RequestBody TempDTO tempData){
		Temp temp = this.service.updateTempDetails(id, tempData);
		if(temp == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(temp, HttpStatus.NO_CONTENT);
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

	@GetMapping(value = "temps/jobId={jobId}")
	public List<Temp> checkTemps(@PathVariable Long jobId){
		return service.checkTemp(jobId); 
	}

}
