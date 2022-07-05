package sewmi.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sewmi.springbootmongodb.model.JiraSprintDTO;

@Repository
public interface JiraSprintRepository extends MongoRepository<JiraSprintDTO,String>{

}
