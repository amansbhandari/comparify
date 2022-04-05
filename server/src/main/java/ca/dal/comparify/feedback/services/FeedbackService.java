package ca.dal.comparify.feedback.services;


import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.item.model.ItemRequestModel;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import ca.dal.comparify.feedback.model.Feedback;
import org.springframework.stereotype.Service;
import ca.dal.comparify.mongo.MongoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Meghna Rupchandani
 */
@Service
public class FeedbackService {

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
    public ArrayList getAll(){
        List<Feedback> mongoResult = mongoRepository.find(FEEDBACK_COLLECTION, Filters.empty(), Feedback.class);
        ArrayList<Map> result = new ArrayList();
        for(Feedback eachFeedbak: mongoResult){
            Map dataDict = new HashMap(){{
                put("date", eachFeedbak.getDate());
                put("email", eachFeedbak.getEmail());
                put("suggestions", eachFeedbak.getSuggestions());
                put("usersFeedback", eachFeedbak.getUsersFeedback());
            }};
            result.add(dataDict);
        }
        return result;
    }

    public long getFeedbackCount(){
        return mongoRepository.count(FEEDBACK_COLLECTION, Filters.empty());
    }
}



