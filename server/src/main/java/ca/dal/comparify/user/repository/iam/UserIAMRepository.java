package ca.dal.comparify.user.repository.iam;

import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.iam.UserIAMModel;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static ca.dal.comparify.constant.ApplicationConstant.DOT;
import static ca.dal.comparify.mongo.MongoUtils.*;
import static ca.dal.comparify.mongo.Tuple.tuple;
import static java.util.Arrays.asList;

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

    public static final String FIELD_SECRET = USER_AUTHORIZATION_KEY + DOT + "secret";

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

    /**
     * @param userId
     * @return
     *
     * @author Harsh Shah
     */
    public UserIAMModel getUserRole(String userId) {

        return mongoRepository.findOne(USER_IAM_COLLECTION,
            eq(UserIAMModel.ID, userId),
            and(tuple(USER_AUTHORIZATION_KEY, 1)),
            UserIAMModel.class);
    }

    /**
     * @param userIdentifier
     * @param key
     * @param value
     * @return
     *
     * @author Harsh Shah
     */
    public boolean updateUserIAMInfo(String userIdentifier, String key, String value) {
        return mongoRepository.updateOne(USER_IAM_COLLECTION,
            eq(UserIAMModel.USER_IDENTIFIER, userIdentifier),
            set(key, value));
    }

    public List<HashModel> checkUserSecretValidity(LocalDate alertBeforeSecretExpirationDays) {

        List<Bson> pipeline = asList(
            match(lte("authentication.secret_expires_on", alertBeforeSecretExpirationDays)),
            project(new Document("days_remaining", dateDiffWithNow("$authentication.secret_expires_on", "day"))
                .append("secret_expires_on", "$authentication.secret_expires_on")),
            lookup("user", "_id",  "_id", "details"),
            unwind( "$details"),
            project(new Document("days_remaining", 1)
                    .append("secret_expires_on", 1)
                    .append("email_id", "$details.email")
                    .append("name",
                        new Document("$concat", asList("$details.firstName", " ", "$details.lastName")))));

        return mongoRepository.aggregate(USER_IAM_COLLECTION, pipeline, HashModel.class);

    }

    /**
     * @return
     *
     * @author Harsh Shah
     */
    public List<HashModel> getAllUsers() {

        List<Bson> pipeline = asList(
            lookup("user", "_id", "_id", "details"),
            unwind("$details"),
            project(new Document("is_active", "$authentication.is_active")
                    .append("username", "$details.username")
                    .append("role", "$authorization.role_id")
                    .append("first_name", "$details.firstName")
                    .append("last_name", "$details.lastName")
                    .append("membership", "$details.type")
                    .append("points", "$details.points")));

        return mongoRepository.aggregate(USER_IAM_COLLECTION, pipeline, HashModel.class);
    }
}
