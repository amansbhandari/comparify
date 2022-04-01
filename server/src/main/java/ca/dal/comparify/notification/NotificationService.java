package ca.dal.comparify.notification;

import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.notification.mail.MailService;
import ca.dal.comparify.framework.notification.model.MailNotificationModel;
import ca.dal.comparify.framework.notification.model.WebPushNotificationModel;
import ca.dal.comparify.framework.notification.model.WebSocketNotificationModel;
import ca.dal.comparify.framework.notification.push.WebPushNotificationService;
import ca.dal.comparify.framework.notification.websocket.WebSocketService;
import ca.dal.comparify.notification.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class NotificationService {

    @Autowired
    private ApplicationScope applicationScope;

    @Autowired
    private WebPushNotificationService webPushNotificationService;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private MailService mailService;

    /**
     * @param userId
     * @param receiverModel
     * @return
     *
     * @author Harsh Shah
     */
    public boolean registerReceiver(String userId, NotificationReceiverModel receiverModel) {
        applicationScope.setUserToReceiverToken(userId, receiverModel.getIdentifier());
        return true;
    }

    /**
     * @param userId
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public boolean send(String userId, WebPushNotificationModel model){
        return webPushNotificationService.send(userId, model);
    }

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public boolean send(String userId, WebSocketNotificationModel model){
        return webSocketService.send(userId, (WebSocketNotificationModel) model);
    }

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public boolean send(String userId, MailNotificationModel model){
        return mailService.send(null, null, null, null);
    }


}
