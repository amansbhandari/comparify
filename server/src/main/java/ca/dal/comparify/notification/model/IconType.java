package ca.dal.comparify.notification.model;

/**
 * @author Harsh Shah
 */
public enum IconType {

    ALERT("ALERT");

    private String value;

    IconType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
