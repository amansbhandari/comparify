package ca.dal.comparify.notification.model;

/**
 * @author Harsh Shah
 */
public class WebPushNotificationModel extends AbstractChannelNotificationModel {

    private String receiverIdentifier;

    public WebPushNotificationModel(String receiverIdentifier, String title, String message, IconType iconType,
                                    NotificationTypeEnum type) {
        super(title, message, iconType, type);
        this.receiverIdentifier = receiverIdentifier;
    }

    public String getReceiverIdentifier() {
        return receiverIdentifier;
    }

    public void setReceiverIdentifier(String receiverIdentifier) {
        this.receiverIdentifier = receiverIdentifier;
    }
}
