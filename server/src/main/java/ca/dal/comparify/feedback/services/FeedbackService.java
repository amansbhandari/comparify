package ca.dal.comparify.feedback.services;


import ca.dal.comparify.feedback.model.Feedback;
import ca.dal.comparify.mongo.MongoRepository;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Meghna Rupchandani
 */
@Service
public class FeedbackService {

    private final String FEEDBACK_COLLECTION = "feedback";
    @Autowired
    private MongoRepository mongoRepository;

    public boolean addFeedback(Feedback f) {
        int result = mongoRepository.insertOne(FEEDBACK_COLLECTION, f, Feedback.class);

        if (result == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Chanpreet Singh
     * Sonar fix by Harsh Shah
     */
    public List<Map<String, Object>> getAll() {
        List<Feedback> mongoResult = mongoRepository.find(FEEDBACK_COLLECTION, Filters.empty(), Feedback.class);
        List<Map<String, Object>> result = new ArrayList();
        for (Feedback eachFeedbak : mongoResult) {
            Map<String, Object> dataDict = new HashMap();
            dataDict.put("date", eachFeedbak.getDate());
            dataDict.put("email", eachFeedbak.getEmail());
            dataDict.put("suggestions", eachFeedbak.getSuggestions());
            dataDict.put("usersFeedback", eachFeedbak.getUsersFeedback());
            result.add(dataDict);
        }
        return result;
    }

    public long getFeedbackCount() {
        return mongoRepository.count(FEEDBACK_COLLECTION, Filters.empty());
    }
}



