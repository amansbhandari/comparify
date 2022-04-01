package ca.dal.comparify.framework.scheduler;

import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.constant.MailTemplateConstant;
import ca.dal.comparify.framework.notification.model.MailNotificationModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.notification.NotificationService;
import ca.dal.comparify.notification.model.IconType;
import ca.dal.comparify.notification.model.NotificationTypeEnum;
import ca.dal.comparify.user.service.iam.UserIAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Harsh Shah
 */
@Configuration
@EnableScheduling
public class SchedulerService {

    @Autowired
    private UserIAMService userIAMService;

    @Autowired
    private NotificationService notificationService;

    /**
     * @author Harsh Shah
     */
    @Scheduled(cron = "* * 0 * * ?")
    public void checkUserSecretValidity() {

        List<HashModel> users = userIAMService.checkUserSecretValidity();

        for (Map<String, Object> user : users) {

            String email = (String) user.get("email_id");
            String id = (String) user.get("_id");

            HashModel model = new HashModel();
            model.put("name", (String) user.get("name"));
            model.put("days", (Long) user.get("days_remaining"));
            model.put("secretExpireDate", (Date) user.get("secret_expires_on"));

            MailNotificationModel notification = new MailNotificationModel("Your account passwords expires soon!",
                MailTemplateConstant.ACCOUNT_SECRET_EXPIRES,
                IconType.ALERT, NotificationTypeEnum.ALERT, id, model);

            notificationService.sendMail(email, notification);

        }
    }
}
