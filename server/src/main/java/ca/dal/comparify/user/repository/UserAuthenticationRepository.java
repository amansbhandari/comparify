package ca.dal.comparify.user.repository;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.authentication.UserAuthenticationModel;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mongodb.client.model.Filters.eq;

@Service
public class UserAuthenticationRepository {

    private static final String USER_AUTH_COLLECTION = "user_auth";

    @Autowired
    private MongoRepository mongoRepository;


    /**
     * @param userIdentifier
     * @return
     */
    public UserAuthenticationModel fetchUserAuthenticationInfo(String userIdentifier) {

        return mongoRepository.findOne(USER_AUTH_COLLECTION,
                eq(UserAuthenticationModel.USER_IDENTIFIER, userIdentifier),
                UserAuthenticationModel.class);
    }

    /**
     * @param userAuthenticationModel
     * @return
     */
    public boolean save(UserAuthenticationModel userAuthenticationModel){

        try {
            mongoRepository.insert(USER_AUTH_COLLECTION, userAuthenticationModel, UserAuthenticationModel.class);
            return true;
        } catch (MongoException e){
            return false;
        }
    }

    /**
     * @param userIdentifier
     * @return
     */
    public boolean isUserExists(String userIdentifier) {
        return mongoRepository.count(USER_AUTH_COLLECTION,
                eq(UserAuthenticationModel.USER_IDENTIFIER, userIdentifier)) > 0;
    }
}
