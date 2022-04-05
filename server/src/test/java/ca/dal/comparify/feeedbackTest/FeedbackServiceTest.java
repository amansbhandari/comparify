package ca.dal.comparify.feeedbackTest;

import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.CompareItemService;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.feedback.model.Feedback;
import ca.dal.comparify.feedback.services.FeedbackService;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.store.StoreService;
import ca.dal.comparify.store.model.StoreModel;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")

public class FeedbackServiceTest {

    /**
     * @author Meghna Rupchandani
     */

    @Autowired
    private FeedbackService feedbackService;

    @MockBean
    private MongoRepository mongoRepository;


    @Test
    void TestaddFeedback(){

        ObjectId id=null;
        LocalDate date=LocalDate.now();
        Feedback feedbackmodel =new Feedback();
        feedbackmodel.setId(id);
        feedbackmodel.setUsersFeedback("DummyData");
        feedbackmodel.setSuggestions("Dummydata");
        feedbackmodel.setDate(date);

        List feedbackmodels =new ArrayList<>();

        feedbackmodels.add(feedbackmodel);

        when(mongoRepository.insertOne(any(),any(),any())).thenReturn(0);
        assertEquals(feedbackService.addFeedback(feedbackmodel), true);
    }



    @Test
    void TestgetAll(){

        ObjectId id=null;
        LocalDate date=LocalDate.now();
        Feedback feedbackmodel =new Feedback();
        feedbackmodel.setId(id);
        feedbackmodel.setUsersFeedback("DummyData");
        feedbackmodel.setSuggestions("Dummydata");
        feedbackmodel.setDate(date);

        List feedbackmodels =new ArrayList<>();

        feedbackmodels.add(feedbackmodel);

        when(feedbackService.getAll()).thenReturn((ArrayList) feedbackmodels);
        assertEquals(feedbackService.getAll().size(),feedbackmodels.size());
    }

    @Test
    void TestgetFeedbackCount(){

        {
            ObjectId id=null;
            LocalDate date=LocalDate.now();
            Feedback feedbackmodel =new Feedback();
            feedbackmodel.setId(id);
            feedbackmodel.setUsersFeedback("DummyData");
            feedbackmodel.setSuggestions("Dummydata");
            feedbackmodel.setDate(date);


            List feedbackmodels =new ArrayList<>();
            when(mongoRepository.count(any(),any())).thenReturn(new Long(2));

            ArrayList listStrings = new ArrayList();
            listStrings.add("6247c13f28da7f42a60913e2");
            listStrings.add("6247c13f28da7f42a60913e2");
            listStrings.add("6247c13f28da7f42a60913e2");
            listStrings.add("6247c13f28da7f42a60913e2");


            feedbackmodels.add(feedbackmodel);

            assertEquals(feedbackService.getFeedbackCount(), new Long(2));
        }

    }
}
