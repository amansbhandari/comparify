package ca.dal.comparify.user.service;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private MongoRepository mongoRepository;

    public int register(SignupRequest signupRequest) {

        signupRequest.setConfirmPassword(null);

        return mongoRepository.insertOne("user", signupRequest, SignupRequest.class);
    }
}
