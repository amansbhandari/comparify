package ca.dal.comparify.brand.model;

/**
 * @author Harsh Shah
 */
public class BrandRequestModel {

    private String name;

    private String description;

    public BrandRequestModel(){}

    public BrandRequestModel(String name, String description) {
        this.name = name;
        this.description = description;
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
}
