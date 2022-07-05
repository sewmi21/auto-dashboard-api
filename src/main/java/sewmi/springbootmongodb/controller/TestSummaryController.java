package sewmi.springbootmongodb.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sewmi.springbootmongodb.model.TestResultsDTO;
import sewmi.springbootmongodb.model.TestSummary;
import sewmi.springbootmongodb.repository.TestSummaryRepository;

@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
public class TestSummaryController {

	@Autowired
	private TestSummaryRepository testSummaryRepository;
	//Add a Single data
			@PostMapping("/testSummary")
			public ResponseEntity<?> createTestResult(@RequestBody TestSummary testSummary){
				try {
					testSummaryRepository.save(testSummary);
					return new ResponseEntity<TestSummary>(testSummary,HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
	
			//Read Single Method Details
			@GetMapping("/testSummary/{id}")
			public ResponseEntity<?> getOneTestResult(@PathVariable String id){
				Optional<TestSummary> testResultsOptional=testSummaryRepository.findById(id);
				if (testResultsOptional.isPresent()) {
					return new ResponseEntity<>(testResultsOptional.get(),HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Test id is not found!"+id,HttpStatus.NOT_FOUND);
				}
			}
			//update single data
			@PutMapping("/testSummary/{id}")
			public ResponseEntity<?> UpdateSingleTestResult(@PathVariable("id")String id,@RequestBody TestSummary tr ){
				Optional<TestSummary> testResultsOptional=testSummaryRepository.findById(id);
				if (testResultsOptional.isPresent()) {
					TestSummary TRSave=testResultsOptional.get();
					TRSave.setTotal(tr.getTotal()  !=null? tr.getTotal() : TRSave.getTotal());
					TRSave.setPassed(tr.getPassed()  !=null? tr.getPassed() : TRSave.getPassed());
					TRSave.setFailed(tr.getFailed()  !=null? tr.getFailed() : TRSave.getFailed());
					TRSave.setSkipped(tr.getSkipped()  !=null? tr.getSkipped() : TRSave.getSkipped());
					testSummaryRepository.save(TRSave);
					return new ResponseEntity<>(testResultsOptional.get(),HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Test id is not found!"+id,HttpStatus.NOT_FOUND);
				}
			}
}
