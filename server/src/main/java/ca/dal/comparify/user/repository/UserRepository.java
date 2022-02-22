package ca.dal.comparify.user.repository;

import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.authentication.UserAuthenticationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mongodb.client.model.Filters.eq;

@Service
public class UserRepository {

    private final static String COLLECTION_NAME = "user";

    @Autowired
    private MongoRepository mongoRepository;

}
