package ca.dal.comparify.model;

/**
 * @author amansinghbhandari
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import ca.dal.comparify.utils.StringUtils;
import org.bson.codecs.pojo.annotations.BsonId;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class AppreciationModel implements Serializable {

    public static final String POINTS = "points";

    public static final String TYPE = "type";

    @BsonId
    private String id;

    @JsonProperty
    private Double points;
    @JsonProperty
    private String type;

    public AppreciationModel() {
        // Create this constructor for Mongo Codec to create object
    }

    public AppreciationModel(String id, Double points, String type) {
        this.id = id;
        this.points = points;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return StringUtils.isAnyEmpty(type);
    }

    public List<String> getRequiredFields(){
        return Arrays.asList(type);
    }
}
