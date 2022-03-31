package ca.dal.comparify.notification.model;

/**
 * @author Harsh Shah
 */
public class NotificationReceiverModel {

    private String identifier;

    public NotificationReceiverModel() {
    }

    public NotificationReceiverModel(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
