package ca.dal.comparify.notification;

import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.notification.mail.MailService;
import ca.dal.comparify.framework.notification.model.MailNotificationModel;
import ca.dal.comparify.framework.notification.model.WebPushNotificationModel;
import ca.dal.comparify.framework.notification.model.WebSocketNotificationModel;
import ca.dal.comparify.framework.notification.push.WebPushNotificationService;
import ca.dal.comparify.framework.notification.websocket.WebSocketService;
import ca.dal.comparify.notification.model.*;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.service.UserDetailsService;
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
    private UserDetailsService userDetailsService;

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
        return webSocketService.send(userId, model);
    }

    /**
     * @param notification
     * @return
     *
     * @author Harsh Shah
     */
    public boolean send(String userId, MailNotificationModel notification){

        UserDetailsModel user = userDetailsService.findUserById(userId);

        return mailService.send(user.getEmail(), notification.getTitle(), notification.getMessage(), notification.getModel());
    }


    /**
     * @param emailId
     * @param notification
     * @return
     *
     * @author Harsh Shah
     */
    public boolean sendMail(String emailId, MailNotificationModel notification){
        return mailService.send(emailId, notification.getTitle(), notification.getMessage(), notification.getModel());
    }


}
