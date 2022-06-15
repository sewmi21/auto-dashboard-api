package sewmi.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sewmi.springbootmongodb.model.TestSummary;

@Repository
public interface TestSummaryRepository extends MongoRepository<TestSummary, String> {

}
