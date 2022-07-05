package sewmi.springbootmongodb.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import sewmi.springbootmongodb.JIRASpringRESTClient;
import sewmi.springbootmongodb.model.JiraSprintDTO;
import sewmi.springbootmongodb.model.TestResultsDTO;
import sewmi.springbootmongodb.repository.JiraSprintRepository;

@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
@Configuration(proxyBeanMethods = false)
public class JiraController {
	private WebClient webClient;
	
	@Autowired
	private JiraSprintRepository jiraSprintRepository;

	@PostMapping("/activeSprint")
	public ResponseEntity<?> createTestResult(@RequestBody JiraSprintDTO jiraSprintDTO){
		try {
			jiraSprintRepository.save(jiraSprintDTO);
			return new ResponseEntity<JiraSprintDTO>(jiraSprintDTO,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	
	
	
	
	//Read Jira Sprint JIRASpringRESTClient
//	@GetMapping("/rest/agile/1.0/board/{boardId}/sprint?state=active")
//	public ResponseEntity<?> getJiraSprintDetails(@PathVariable String boardId){
//		Optional<JiraSprintDTO> jiraSprintOptional=jiraSprintRepository.findById(boardId);
//		if (jiraSprintOptional.isPresent()) {
//			return new ResponseEntity<>(jiraSprintOptional.get(),HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("Test id is not found!"+boardId,HttpStatus.NOT_FOUND);
//		}
//	}
}
