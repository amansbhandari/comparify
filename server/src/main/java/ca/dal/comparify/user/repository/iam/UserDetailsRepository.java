package ca.dal.comparify.user.repository.iam;


import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.model.iam.UserDetailsRequestModel;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ca.dal.comparify.mongo.MongoUtils.and;
import static ca.dal.comparify.mongo.MongoUtils.set;
import static ca.dal.comparify.mongo.MongoUtils.eq;
import static ca.dal.comparify.mongo.Tuple.tuple;

/**
 * @author Aman Singh Bhandari
 */

@Service
public class UserDetailsRepository {
    @Autowired
    private MongoRepository mongoRepository;

    private static final String USER_DETAILS_COLLECTION = "user";
    private static final String USER_PASSWORD= "password";
    private static final String USERNAME = "username";

    private static final String EMAIL_ID = "email";

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    /**
     * @param username
     * @return
     * @author Aman Singh Bhandari
     */
    public UserDetailsModel fetchUserDetails(String username) {

        UserDetailsModel userDetailsModel = mongoRepository.findOne(USER_DETAILS_COLLECTION,
                eq(UserDetailsModel.USERNAME_KEY, username),
                and(tuple(USER_PASSWORD, 0)),
                UserDetailsModel.class);

        return userDetailsModel;
    }


    /**
     * @param userDetailsRequestModel
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean saveUserDetails(UserDetailsRequestModel userDetailsRequestModel) {
        Bson query = eq(UserDetailsModel.USERNAME_KEY, userDetailsRequestModel.getUsername());
        Bson[] values = {set(EMAIL_ID,userDetailsRequestModel.getEmail()), set(FIRST_NAME, userDetailsRequestModel.getFirstName()), set(LAST_NAME,userDetailsRequestModel.getLastName())};
        Boolean result = mongoRepository.updateOne(USER_DETAILS_COLLECTION,query, values);

        return result;
    }

    /**
     * @param id
     * @return
     *
     * @author Harsh Shah
     */
    public UserDetailsModel findUserById(String id) {

        return mongoRepository.findOne(USER_DETAILS_COLLECTION,
            eq("_id", id),
            and(tuple(USER_PASSWORD, 0)),
            UserDetailsModel.class);
    }

    /**
     * @author Chanpreet Singh
     */
    public Long getUserCount() {
        return mongoRepository.count(USER_DETAILS_COLLECTION, Filters.empty());
    }
}
