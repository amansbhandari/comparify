package ca.dal.comparify.securityQuestion.repository;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.securityQuestion.model.Question;
import static ca.dal.comparify.mongo.MongoUtils.eq;
import static ca.dal.comparify.mongo.MongoUtils.set;
import static ca.dal.comparify.mongo.MongoUtils.and;
import java.util.List;




@Service
public class QuestionRepository  {
    @Autowired
    private MongoRepository mongoRepository;    
    static final String COLLECTION_NAME = "securityQuestion";
    static final String ID = "userIdentifier";
    static final String QUESTION = "question";
    static final String ANSWER = "answer";
    Class<Question> classOf = Question.class;


    public int addQuestion(Question question) {
        return mongoRepository.insertOne(COLLECTION_NAME,question, classOf);
    }
    public Question getOneQuestionById(String userIdentifier ) {
        return mongoRepository.findOne(COLLECTION_NAME,eq(ID, userIdentifier), classOf);
    }
    public List<Question> getAllQuestion(String userIdentifier) {
      
        return mongoRepository.find(COLLECTION_NAME,eq(ID,userIdentifier) , classOf); 
    }
    public boolean updateAnswer(String userIdentifier, String question, String answer) {
        Bson query = and(eq(ID, userIdentifier), eq(QUESTION, question));
        Bson[] values = {set(ANSWER, answer)}; 
        return mongoRepository.updateOne(COLLECTION_NAME, query,values);
    }
    public boolean deleteQuestion(String userIdentifier, String question) {
        Bson query = and(eq(ID, userIdentifier), eq(QUESTION, question));
        return mongoRepository.deleteOne(COLLECTION_NAME,query);
    }

 
}
