package sewmi.springbootmongodb.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sewmi.springbootmongodb.model.TestResultsDTO;
import sewmi.springbootmongodb.repository.TestResultsRepository;

@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
public class TestResultsController {
	
		@Autowired
		private TestResultsRepository testResultRepo;
		
		//Read All data
		@GetMapping("/testResults")
		public ResponseEntity<?> getAllTestResults(){
			List<TestResultsDTO> testresults =testResultRepo.findAll();
			if(testresults.size()>0) {
				return new ResponseEntity<List<TestResultsDTO>>(testresults,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("No Test Results Available!",HttpStatus.NOT_FOUND);
			}
		}
		//Add a Single data
		@PostMapping("/testResults")
		public ResponseEntity<?> createTestResult(@RequestBody TestResultsDTO testResult){
			try {
				testResult.setCreatedAt(new Date(System.currentTimeMillis()));
				testResultRepo.save(testResult);
				return new ResponseEntity<TestResultsDTO>(testResult,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		//Read Single Method Details
		@GetMapping("/testResults/{testMethodName}")
		public ResponseEntity<?> getSingleTestResult(@PathVariable String testMethodName){
			Optional<TestResultsDTO> testResultsOptional=testResultRepo.findBy(null, null);
			if (testResultsOptional.isPresent()) {
				return new ResponseEntity<>(testResultsOptional.get(),HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Test Result is not found!"+testMethodName,HttpStatus.NOT_FOUND);
			}
		}
		//Updating method with results
		@PutMapping("/testResults/{testMethodName}")
		public ResponseEntity<?> UpdateSingleTestResult(@PathVariable("testMethodName")String testMethodName,@RequestBody TestResultsDTO tr ){
			Optional<TestResultsDTO> testResultsOptional=testResultRepo.findById(testMethodName);
			if (testResultsOptional.isPresent()) {
				TestResultsDTO TRSave=testResultsOptional.get();
				TRSave.setTestMethodName(tr.getTestMethodName() !=null ? tr.getTestMethodName() : TRSave.getTestMethodName());
				TRSave.setTestResult(tr.getTestResult() !=null ? tr.getTestResult() : TRSave.getTestResult());
				TRSave.setUpdatedAt(new Date(System.currentTimeMillis()));
				testResultRepo.save(TRSave);
				return new ResponseEntity<>(testResultsOptional.get(),HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Test Result is not found!"+testMethodName,HttpStatus.NOT_FOUND);
			}
		}
		
		@DeleteMapping("/testResults/{testMethodName}")
		public ResponseEntity<?> deleteBy(@PathVariable("testMethodName")String testMethodName){
			try {
				testResultRepo.deleteById(testMethodName);
				return new ResponseEntity<>("Successfully deleted with method name!"+testMethodName,HttpStatus.OK);
			} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			}
		}
	}


