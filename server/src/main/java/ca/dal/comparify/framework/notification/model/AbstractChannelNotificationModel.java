package ca.dal.comparify.framework.notification.model;

import ca.dal.comparify.notification.model.IconType;
import ca.dal.comparify.notification.model.NotificationTypeEnum;

/**
 * @author Harsh Shah
 */
public class AbstractChannelNotificationModel {

    private String title;

    private String message;

    private IconType iconType;

    private NotificationTypeEnum type;

    protected AbstractChannelNotificationModel(String title, String message,
                                            IconType iconType, NotificationTypeEnum type) {
        this.title = title;
        this.message = message;
        this.iconType = iconType;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IconType getIconType() {
        return iconType;
    }

    public void setIconType(IconType iconType) {
        this.iconType = iconType;
    }
}
