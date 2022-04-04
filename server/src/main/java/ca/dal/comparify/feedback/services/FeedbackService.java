package ca.dal.comparify.feedback.services;


import ca.dal.comparify.feedback.repository.FeedbackRepository;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.item.model.ItemRequestModel;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import ca.dal.comparify.feedback.model.Feedback;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import ca.dal.comparify.mongo.MongoRepository;

import java.util.Collections;
import java.util.List;


/**
 * @author Meghna Rupchandani
 */
@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private MongoRepository mongoRepository;

    private final String FEEDBACK_COLLECTION = "feedback";

    public boolean addFeedback( Feedback f) {
        int result = mongoRepository.insertOne(FEEDBACK_COLLECTION, f , Feedback.class);

        if(result == 0)
        { return true;}
        else
        {return false;}
    }

    /**
     * @author Chanpreet Singh
     */
    public long getFeedbackCount(){
        return mongoRepository.count(FEEDBACK_COLLECTION, Filters.empty());
    }
}



