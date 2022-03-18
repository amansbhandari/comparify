package ca.dal.comparify.securityQuestion.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.dal.comparify.mongo.MongoRepository;

@Component
public class DemoLoader implements CommandLineRunner {

    @Autowired
    private final MongoRepository questionRepository;
    
    public DemoLoader(MongoRepository questionRepository) {
        this.questionRepository = questionRepository;
    }         
    @Override
    public void run(String... args) throws Exception {
     
        this.questionRepository.save(new Question( "What is your mother's maiden name?", null));
        this.questionRepository.save(new Question( "What is your father's middle name?", null));
        this.questionRepository.save(new Question("What was the name of your first pet?", null));
    }
}
    
