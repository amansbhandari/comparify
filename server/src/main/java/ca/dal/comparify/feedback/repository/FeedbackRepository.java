package ca.dal.comparify.feedback.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import ca.dal.comparify.feedback.model.Feedback;
import org.springframework.stereotype.Repository;


/**
 * @author Meghna Rupchandani
 */

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, String> {

}





