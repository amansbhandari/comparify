package ca.dal.comparify.Appreciation;

import ca.dal.comparify.model.AppreciationModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.model.iam.UserDetailsRequestModel;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ca.dal.comparify.mongo.MongoUtils.eq;
import static ca.dal.comparify.mongo.MongoUtils.set;

/**
 * @author amansinghbhandari
 */
@Service
public class AppreciationRepository {

    @Autowired
    private MongoRepository mongoRepository;

    private String ID = "_id";

    public static final String USER_DETAILS_COLLECTION = "user";
    /**
     * @param userDetailsRequestModel
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean saveUserAppreciation(AppreciationModel appreciationModel) {
        Bson query = eq(ID, appreciationModel.getId());
        Bson[] values = {set(AppreciationModel.POINTS,appreciationModel.getPoints()), set(AppreciationModel.TYPE, appreciationModel.getType())};
        Boolean result = mongoRepository.updateOne(USER_DETAILS_COLLECTION,query, values);

        return result;
    }
}
