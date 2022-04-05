package ca.dal.comparify.user.model.iam;

/**
 * @author amansinghbhandari
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import ca.dal.comparify.utils.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserDetailsRequestModel implements Serializable {

    private static final String USERNAME_KEY = "username";

    private static final String EMAIL_ID = "email";

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";



    @JsonProperty(USERNAME_KEY)
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    public UserDetailsRequestModel() {
        // Create this constructor for Mongo Codec to create object
    }

    public UserDetailsRequestModel(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public boolean isEmpty(){
        return StringUtils.isAnyEmpty(email,firstName,lastName,username);
    }

    public List<String> getRequiredFields(){
        return Arrays.asList(email,firstName,lastName,username);
    }
}
