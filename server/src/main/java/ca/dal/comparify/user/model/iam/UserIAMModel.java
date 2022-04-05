package ca.dal.comparify.user.model.iam;

import ca.dal.comparify.user.model.iam.authentication.UserAuthenticationModel;
import ca.dal.comparify.user.model.iam.authorization.UserAuthorizationModel;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Harsh Shah
 */
public class UserIAMModel {

    public static final String ID_KEY = "_id";
    public static final String USER_IDENTIFIER = "user_identifier";

    @BsonId
    private String id;

    @BsonProperty(USER_IDENTIFIER)
    private String userIdentifier;

    private UserAuthenticationModel authentication;

    private UserAuthorizationModel authorization;

    public UserIAMModel() {
        // Create this constructor for Mongo Codec to create object
    }

    public UserIAMModel(@BsonId String id,
                        @BsonProperty(USER_IDENTIFIER) String userIdentifier,
                        @BsonProperty("authentication") UserAuthenticationModel authentication,
                        @BsonProperty("authorization") UserAuthorizationModel authorization) {
        this.id = id;
        this.userIdentifier = userIdentifier;
        this.authentication = authentication;
        this.authorization = authorization;
    }

    public UserIAMModel(String userIdentifier, UserAuthenticationModel authentication, UserAuthorizationModel authorization) {
        this.id = UUIDUtils.generate();
        this.userIdentifier = userIdentifier;
        this.authentication = authentication;
        this.authorization = authorization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public UserAuthenticationModel getAuthentication() {
        return authentication;
    }

    public void setAuthentication(UserAuthenticationModel authentication) {
        this.authentication = authentication;
    }

    public UserAuthorizationModel getAuthorization() {
        return authorization;
    }

    public void setAuthorization(UserAuthorizationModel authorization) {
        this.authorization = authorization;
    }
}
