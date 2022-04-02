package ca.dal.comparify.item.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chanpreet Singh
 */
public class ItemRequestModel {

    private String name;

    private String description, itemCategoryId;

    @JsonProperty("default_image")
    private String defaultImage;

    public ItemRequestModel() {
    }

    public ItemRequestModel(String name, String description, String defaultImage, String itemCategoryId) {
        this.name = name;
        this.description = description;
        this.defaultImage = defaultImage;
        this.itemCategoryId = itemCategoryId;
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

    public String getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(String itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }
}
