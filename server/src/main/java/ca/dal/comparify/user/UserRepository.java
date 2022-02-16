package ca.dal.comparify.user;

import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.user.model.authentication.UserAuthenticationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class UserRepository {

    @Autowired
    private MongoRepository mongoRepository;

    public UserAuthenticationModel fetchUserAuthenicationInfo(String userIdentifier) {

        // create query

        // execute

        // transform

        List<HashModel> user = mongoRepository.find("user", eq("id", "123545879"),
                HashModel.class);

        return null;
    }
}
