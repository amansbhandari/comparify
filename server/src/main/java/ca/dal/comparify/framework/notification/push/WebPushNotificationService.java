package ca.dal.comparify.framework.notification.push;

import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.properties.PushNotificationConfigurationProperties;
import ca.dal.comparify.notification.model.WebPushNotificationModel;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import static ca.dal.comparify.constant.NotificationConstant.TTL;

/**
 * @author Harsh Shah
 */
@Service
public class WebPushNotificationService {

    @Autowired
    private PushNotificationConfigurationProperties pushNotificationConfigurationProperties;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationScope applicationScope;


    public WebPushNotificationService(PushNotificationConfigurationProperties pushNotificationConfigurationProperties,
                                      ResourceLoader resourceLoader) {

        Resource resource = resourceLoader.getResource(pushNotificationConfigurationProperties.getServiceAccountFile());

        try (InputStream serviceAccount = resource.getInputStream()) {

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {

        }
    }

    /**
     * @param userId
     * @param model
     * @return
     */
    public boolean send(String userId, WebPushNotificationModel model) {

        Message message = Message.builder()
            .setToken(applicationScope.getUserToReceiverToken(userId))
            .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", TTL)
                .setNotification(new WebpushNotification(model.getTitle(),
                    model.getMessage(),
                    model.getIconType().getValue()))
                .build())
            .build();

        try {
            FirebaseMessaging.getInstance().sendAsync(message).get();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        }

        return true;
    }
}
