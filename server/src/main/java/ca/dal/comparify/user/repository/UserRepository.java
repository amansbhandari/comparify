package ca.dal.comparify.user.repository;

import ca.dal.comparify.mongo.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {

    private final static String COLLECTION_NAME = "user";

    @Autowired
    private MongoRepository mongoRepository;

}
