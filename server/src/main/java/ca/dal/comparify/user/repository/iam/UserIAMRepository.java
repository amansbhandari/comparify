package ca.dal.comparify.user.repository.iam;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.iam.UserIAMModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ca.dal.comparify.constant.ApplicationConstant.DOT;
import static ca.dal.comparify.mongo.MongoUtils.*;
import static ca.dal.comparify.mongo.Tuple.tuple;

/**
 * @author Harsh Shah
 */
@Service
public class UserIAMRepository {

    public static final String USER_IAM_COLLECTION = "user_identity_management";

    public static final String USER_AUTHORIZATION_KEY = "authorization";

    public static final String USER_AUTHENTICATION_KEY = "authentication";
    public static final String FIELD_NUMBER_OF_INCORRECT_ATTEMPTS = USER_AUTHENTICATION_KEY + DOT +
            "number_of_incorrect_attempts";

    @Autowired
    private MongoRepository mongoRepository;


    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    public UserIAMModel fetchUserAuthenticationInfo(String userIdentifier) {

        return mongoRepository.findOne(USER_IAM_COLLECTION,
                eq(UserIAMModel.USER_IDENTIFIER, userIdentifier),
                and(tuple(USER_AUTHENTICATION_KEY, 1),
                        tuple(UserIAMModel.USER_IDENTIFIER, 1)),
                UserIAMModel.class);
    }

    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    public boolean isUserExists(String userIdentifier) {
        return mongoRepository.count(USER_IAM_COLLECTION,
                eq(UserIAMModel.USER_IDENTIFIER, userIdentifier)) > 0;
    }

    /**
     * @param userIAMModel
     * @return
     * @author Harsh Shah
     */
    public int save(UserIAMModel userIAMModel) {
        return mongoRepository.insertOne(USER_IAM_COLLECTION, userIAMModel, UserIAMModel.class);
    }

    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    public boolean invalidAuthenticationAttempt(String userIdentifier) {
        return mongoRepository.updateOne(USER_IAM_COLLECTION,
                eq(UserIAMModel.USER_IDENTIFIER, userIdentifier),
                inc(FIELD_NUMBER_OF_INCORRECT_ATTEMPTS, 1));
    }

    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    public boolean validAuthenticationAttempt(String userIdentifier) {
        return mongoRepository.updateOne(USER_IAM_COLLECTION,
                eq(UserIAMModel.USER_IDENTIFIER, userIdentifier),
                set(FIELD_NUMBER_OF_INCORRECT_ATTEMPTS, 0));
    }
}
