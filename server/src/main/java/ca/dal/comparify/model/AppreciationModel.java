package ca.dal.comparify.model;

/**
 * @author amansinghbhandari
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import ca.dal.comparify.utils.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class AppreciationModel implements Serializable {

    public static final String USERNAME_KEY = "username";

    public static final String POINTS_KEY = "points";

    public static final String TYPE_KEY = "type";



    @JsonProperty(USERNAME_KEY)
    private String username;

    private Double points;


    private String type;

    public AppreciationModel() {
        // Create this constructor for Mongo Codec to create object
    }

    public AppreciationModel(String username, Double points, String type) {
        this.username = username;
        this.points = points;
        this.type = type;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isEmpty(){
        return StringUtils.isAnyEmpty(type,username);
    }

    public List<String> getRequiredFields(){
        return Arrays.asList(type,username);
    }
}
