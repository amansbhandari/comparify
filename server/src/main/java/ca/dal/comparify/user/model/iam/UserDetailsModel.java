package ca.dal.comparify.user.model.iam;

import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Arrays;
import java.util.List;


/**
 * @author Aman Singh Bhandari
 */
public class UserDetailsModel {

    public static final String USERNAME = "username";

    @BsonId
    private String id;

    @BsonProperty(USERNAME)
    private String username;

    private String email;
    private String firstName;
    private String lastName;


    public UserDetailsModel() {
        // Create this constructor for Mongo Codec to create object
    }

    public UserDetailsModel(@BsonId String id,
                        @BsonProperty(USERNAME) String username,
                        @BsonProperty("email") String email,
                        @BsonProperty("firstName") String firstName,
                            @BsonProperty("lastName") String lastName   ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName  = firstName;
        this.lastName = lastName;
    }

    public UserDetailsModel(String username, String email, String firstName, String lastName) {
        this.id = UUIDUtils.generate();
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String lastName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static List<String> getRequiredFields(){
        return Arrays.asList(USERNAME);
    }

 }
