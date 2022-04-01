package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.framework.notification.model.AbstractChannelNotificationModel;
import ca.dal.comparify.framework.notification.model.WebPushNotificationModel;
import ca.dal.comparify.framework.notification.model.WebSocketNotificationModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.notification.NotificationService;
import ca.dal.comparify.notification.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static ca.dal.comparify.alerts.model.AlertTypeEnum.PRODUCT_INFORMATION_AVAILABLE;
import static ca.dal.comparify.utils.ObjectUtils.convert;

/**
 * @author Harsh Shah
 */
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private NotificationService notificationService;

    /**
     * @param model
     * @param createdBy
     * @return
     *
     * @author Harsh Shah
     */
    public int create(AlertRequestModel model, String createdBy) {
        return alertRepository.save(AlertModel.transform(model, createdBy));
    }

    /**
     * @param userIdentifier
     * @return
     *
     * @author Harsh Shah
     */
    public List<AlertResponseModel> fetch(String userIdentifier) {
        return alertRepository.getAlerts(userIdentifier);
    }

    /**
     * @param userId
     * @param id
     * @return
     *
     * @author Harsh Shah
     */
    public boolean delete(String userId, String id) {
        return alertRepository.delete(userId, id);
    }

    /**
     * @param brandId
     * @param productId
     *
     * @author Harsh Shah
     */
    public void trigger(String brandId, String productId) {

        HashModel alerts = checkForAlerts(brandId, productId);

        List<AlertModel> alertsOnInformationAvailable =
             convert((List<Object>) alerts.get(PRODUCT_INFORMATION_AVAILABLE.getValueLowerCase()), AlertModel.class);

        List<AlertModel> alertsOnPriceDrop =
            convert((List<Object>) alerts.get(PRODUCT_INFORMATION_AVAILABLE.getValueLowerCase()), AlertModel.class);

        List<AlertModel> alertsOnPriceRange =
            convert((List<Object>) alerts.get(PRODUCT_INFORMATION_AVAILABLE.getValueLowerCase()), AlertModel.class);


        triggerSocket(alertsOnInformationAvailable, "Product Information Available");
        triggerWebPush(alertsOnInformationAvailable, "Product Information Available");

        triggerSocket(alertsOnPriceDrop, "Price Dropped");
        triggerWebPush(alertsOnPriceDrop, "Price Dropped");

        triggerSocket(alertsOnPriceRange, "Available in Range");
        triggerWebPush(alertsOnPriceRange, "Available in Range");

        //triggerMail(activeAlerts);
    }

    private void triggerMail(List<AlertModel> activeAlerts) {
    }

    /**
     * @param alerts
     * @param message
     *
     * @author Harsh Shah
     */
    private void triggerWebPush(List<AlertModel> alerts, String message) {
        if (alerts.isEmpty()) {
            return;
        }

        for (AlertModel alert : alerts) {
            WebPushNotificationModel model = new WebPushNotificationModel(
                alert.getAudit().getCreatedBy(), alert.getAlertIdentifier(),
                message, IconType.ALERT, NotificationTypeEnum.ALERT);

            notificationService.send(alert.getAudit().getCreatedBy(), model);
        }
    }


    /**
     * @param alerts
     * @param message
     *
     * @author Harsh Shah
     */
    private void triggerSocket(List<AlertModel> alerts, String message) {
        if (alerts.isEmpty()) {
            return;
        }

        for (AlertModel alert : alerts) {
            String userId = alert.getAudit().getCreatedBy();
            WebSocketNotificationModel model = new WebSocketNotificationModel(
                Collections.singletonList(userId),
                alert.getAlertIdentifier(), message, IconType.ALERT, NotificationTypeEnum.ALERT);

            notificationService.send(userId, model);
        }
    }

    /**
     * @param brandId
     * @param itemId
     * @return
     *
     * @author Harsh Shah
     */
    private HashModel checkForAlerts(String brandId, String itemId) {
        return alertRepository.checkForAlerts(brandId, itemId);
    }
}
