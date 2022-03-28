package ca.dal.comparify.item.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Harsh Shah
 */
public class ItemRequestModel {

    private String name;

    private String description;

    @JsonProperty("default_image")
    private String defaultImage;

    public ItemRequestModel() {
    }

    public ItemRequestModel(String name, String description, String defaultImage) {
        this.name = name;
        this.description = description;
        this.defaultImage = defaultImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }
}
