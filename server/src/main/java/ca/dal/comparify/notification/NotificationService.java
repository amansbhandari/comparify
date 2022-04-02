package ca.dal.comparify.notification;

import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.notification.mail.MailService;
import ca.dal.comparify.framework.notification.model.MailNotificationModel;
import ca.dal.comparify.framework.notification.model.WebPushNotificationModel;
import ca.dal.comparify.framework.notification.model.WebSocketNotificationModel;
import ca.dal.comparify.framework.notification.push.WebPushNotificationService;
import ca.dal.comparify.framework.notification.websocket.WebSocketService;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.notification.model.NotificationModel;
import ca.dal.comparify.notification.model.NotificationReceiverModel;
import ca.dal.comparify.notification.model.NotificationTypeEnum;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Harsh Shah
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

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
     * @author Harsh Shah
     */
    public boolean registerReceiver(String userId, NotificationReceiverModel receiverModel) {
        applicationScope.setUserToReceiverToken(userId, receiverModel.getIdentifier());
        return true;
    }

    /**
     * @param userId
     * @param notification
     * @return
     * @author Harsh Shah
     */
    public boolean send(String userId, WebPushNotificationModel notification) {
        return webPushNotificationService.send(userId, notification);
    }

    /**
     * @param notification
     * @return
     * @author Harsh Shah
     */
    public boolean send(String userId, WebSocketNotificationModel notification) {
        return webSocketService.send(userId, notification);
    }

    /**
     * @param notification
     * @return
     * @author Harsh Shah
     */
    public boolean send(String userId, MailNotificationModel notification) {

        UserDetailsModel user = userDetailsService.findUserById(userId);

        return mailService.send(user.getEmail(), notification.getTitle(),
            notification.getMessage(), notification.getModel());
    }


    /**
     * @param userId
     * @param emailId
     * @param notification
     * @return
     * @author Harsh Shah
     */
    public boolean sendMail(String userId, String emailId, MailNotificationModel notification) {
        return mailService.send(emailId, notification.getTitle(),
            notification.getMessage(), notification.getModel());

    }

    /**
     * @param userId
     * @param title
     * @param message
     * @param type
     * @author Harsh Shah
     */
    public void create(String userId, String title, String message, NotificationTypeEnum type) {
        notificationRepository.add(
            new NotificationModel(userId, title, message,
                type, new HashModel()));
    }

    /**
     * @param userId
     * @return
     * @author Harsh Shah
     */
    public List<NotificationModel> fetch(String userId) {
        return notificationRepository.fetch(userId);
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
