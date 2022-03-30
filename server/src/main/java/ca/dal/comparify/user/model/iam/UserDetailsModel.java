package ca.dal.comparify.user.model.iam;

import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;


/**
 * @author Aman Singh Bhandari
 */
public class UserDetailsModel {

    public static final String USERNAME = "username";

    @BsonProperty("_id")
    @BsonId
    private String id;

    @BsonProperty(USERNAME)
    private String username;

    private String email;
    private String firstName;
    private String lastName;
    //---Issue #53 and 54
    private Double points;
    private String type;


    public UserDetailsModel() {
        // Create this constructor for Mongo Codec to create object
    }

    public UserDetailsModel(@BsonId String id,
                        @BsonProperty(USERNAME) String username,
                        @BsonProperty("email") String email,
                        @BsonProperty("firstName") String firstName,
                            @BsonProperty("lastName") String lastName,
                            @BsonProperty("point") Double points,
                            @BsonProperty("type") String type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName  = firstName;
        this.lastName = lastName;
        this.points = points;
        this.type = type;
    }

    public UserDetailsModel(String username, String email, String firstName, String lastName, Double point, String types) {
        //this.id = UUIDUtils.generate();
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.type = type;
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
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getPoints() {return points;}

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getType() {return type;}

    public void setType(String type) {
        this.type = type;
    }

    public static List<String> getRequiredFields(){
        return Arrays.asList(USERNAME);
    }

 }
