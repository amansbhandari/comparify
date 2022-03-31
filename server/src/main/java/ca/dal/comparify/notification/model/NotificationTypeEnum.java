package ca.dal.comparify.notification.model;

/**
 * @author Harsh Shah
 */
public enum NotificationTypeEnum {

    ALERT("ALERT");

    private String value;

    NotificationTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
