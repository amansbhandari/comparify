package ca.dal.comparify.notification.model;

import ca.dal.comparify.model.HashModel;

/**
 * @author Harsh Shah
 */
public class MailNotificationModel extends AbstractChannelNotificationModel {

    private String receiverIdentifier;

    private HashModel model;

    public MailNotificationModel(String title, String message, IconType iconType, NotificationTypeEnum type,
                                 String receiverIdentifier, HashModel model) {
        super(title, message, iconType, type);
        this.receiverIdentifier = receiverIdentifier;
        this.model = model;
    }

    public String getReceiverIdentifier() {
        return receiverIdentifier;
    }

    public void setReceiverIdentifier(String receiverIdentifier) {
        this.receiverIdentifier = receiverIdentifier;
    }

    public HashModel getModel() {
        return model;
    }

    public void setModel(HashModel model) {
        this.model = model;
    }
}
