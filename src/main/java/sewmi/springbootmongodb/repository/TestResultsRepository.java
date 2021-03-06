package sewmi.springbootmongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sewmi.springbootmongodb.model.TestResultsDTO;

@Repository
public interface TestResultsRepository extends MongoRepository<TestResultsDTO, String> {
	Optional<TestResultsDTO> findBytestMethodName(String TestMethodName);

	
}
