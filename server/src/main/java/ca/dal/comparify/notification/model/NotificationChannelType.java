package ca.dal.comparify.notification.model;

/**
 * @author Harsh Shah
 */
public enum NotificationChannelType {

    MAIL("MAIL"), SOCKET("SOCKET"), PUSH("PUSH");

    private String value;

    NotificationChannelType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
