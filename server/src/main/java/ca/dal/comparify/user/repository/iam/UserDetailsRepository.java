package ca.dal.comparify.user.repository.iam;


import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.model.iam.UserIAMModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ca.dal.comparify.mongo.MongoUtils.and;
import static ca.dal.comparify.mongo.MongoUtils.eq;
import static ca.dal.comparify.mongo.Tuple.tuple;

/**
 * @author Aman Singh Bhandari
 */

@Service
public class UserDetailsRepository {
    @Autowired
    private MongoRepository mongoRepository;

    public static final String USER_DETAILS_COLLECTION = "user";
    public static final String USER_PASSWORD= "password";

    /**
     * @param username
     * @return
     * @author Aman Singh Bhandari
     */
    public UserDetailsModel fetchUserDetails(String username) {

        UserDetailsModel userDetailsModel = mongoRepository.findOne(USER_DETAILS_COLLECTION,
                eq(UserDetailsModel.USERNAME, username),
                and(tuple(USER_PASSWORD, 0)),
                UserDetailsModel.class);

        return userDetailsModel;
    }
}
