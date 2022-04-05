package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.brand.BrandService;
import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.constant.MailTemplateConstant;
import ca.dal.comparify.framework.notification.model.MailNotificationModel;
import ca.dal.comparify.framework.notification.model.WebPushNotificationModel;
import ca.dal.comparify.framework.notification.model.WebSocketNotificationModel;
import ca.dal.comparify.item.ItemService;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.notification.NotificationService;
import ca.dal.comparify.notification.model.IconType;
import ca.dal.comparify.notification.model.NotificationTypeEnum;
import ca.dal.comparify.store.StoreService;
import ca.dal.comparify.store.model.StoreModel;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;

import static ca.dal.comparify.alerts.model.AlertTypeEnum.*;
import static ca.dal.comparify.utils.DateUtils.zoneNow;
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

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private StoreService storeService;

    /**
     * @param alert
     * @param createdBy
     * @return
     * @author Harsh Shah
     */
    public int create(AlertRequestModel alert, String createdBy) {
        int status = alertRepository.save(AlertModel.transform(alert, createdBy));

        if (status == 0) {

            String title = String.format("Successful Creation of Alert - %s", alert.getAlertIdentifier());
            HashModel model = createMailModel(createdBy, alert.getAlertIdentifier());

            notificationService.send(createdBy,
                new MailNotificationModel(title, MailTemplateConstant.ALERT_UNSUBSCRIBE_TEMPLATE,
                    IconType.ALERT, NotificationTypeEnum.ALERT, createdBy, model));
        }

        return status;
    }


    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    public List<AlertResponseModel> fetch(String userIdentifier) {
        return alertRepository.getAlerts(userIdentifier);
    }

    /**
     * @param userId
     * @param id
     * @return
     * @author Harsh Shah
     */
    public boolean delete(String userId, String id) {

        AlertModel alert = fetchAlertById(id);
        if (alert == null) {
            return false;
        }

        boolean status = alertRepository.delete(userId, id);

        if (status) {

            String title = String.format("Successful Deletion of Alert - %s", alert.getAlertIdentifier());
            HashModel model = createMailModel(userId, alert.getAlertIdentifier());

            notificationService.send(userId,
                new MailNotificationModel(title, MailTemplateConstant.ALERT_UNSUBSCRIBE_TEMPLATE,
                    IconType.ALERT, NotificationTypeEnum.ALERT, userId, model));
        }

        return status;

    }

    /**
     * @param brandId
     * @param productId
     * @param price
     * @param storeId
     * @author Harsh Shah
     */
    public void trigger(String brandId, String productId, Double price, String storeId) {

        HashModel alerts = checkForAlerts(brandId, productId);

        List<AlertModel> alertsOnInformationAvailable =
            convert((List<Object>) alerts.get(PRODUCT_INFORMATION_AVAILABLE.getValueLowerCase()), AlertModel.class);

        List<AlertModel> alertsOnPriceDrop =
            convert((List<Object>) alerts.get(PRICE_DROP.getValueLowerCase()), AlertModel.class);

        List<AlertModel> alertsOnPriceRange =
            convert((List<Object>) alerts.get(PRICE_RANGE.getValueLowerCase()), AlertModel.class);


        if(alertsOnInformationAvailable.isEmpty() &&
            alertsOnPriceDrop.isEmpty() &&
            alertsOnPriceRange.isEmpty()){
            return;
        }

        BrandModel brand = brandService.findBrandById(brandId);
        ItemModel item = itemService.findItemById(productId);
        StoreModel store = storeService.findStoreById(storeId);

        String itemName = item.getName();
        String brandName = brand.getName();
        String storeName = store.getStoreName();


        String message = String.format("Information on %s of brand %s is available in %s for price %d",
            itemName, brandName, storeName, price);

        for (AlertModel alert : alertsOnInformationAvailable) {
            notificationService.create(alert.getAudit().getCreatedBy(),
                alert.getAlertIdentifier(), message, NotificationTypeEnum.ALERT);
        }
        triggerSocket(alertsOnInformationAvailable, message);
        triggerWebPush(alertsOnInformationAvailable, message);
        triggerMail(alertsOnInformationAvailable, message);


        message = String.format("Price dropped on the %s of brand %s in store %s",
            itemName, brandName, storeName);
        for (AlertModel alert : alertsOnPriceDrop) {
            notificationService.create(alert.getAudit().getCreatedBy(),
                alert.getAlertIdentifier(), message, NotificationTypeEnum.ALERT);
        }
        triggerSocket(alertsOnPriceDrop, message);
        triggerWebPush(alertsOnPriceDrop, message);
        triggerMail(alertsOnPriceDrop, message);


        message = String.format("%s is available in price range of brand %s in store %s",
            itemName, brandName, storeName);
        for (AlertModel alert : alertsOnPriceRange) {
            notificationService.create(alert.getAudit().getCreatedBy(),
                alert.getAlertIdentifier(), message, NotificationTypeEnum.ALERT);
        }
        triggerSocket(alertsOnPriceRange, message);
        triggerWebPush(alertsOnPriceRange, message);
        triggerMail(alertsOnPriceRange, message);

    }

    private void triggerMail(List<AlertModel> activeAlerts, String message) {
        if (activeAlerts.isEmpty()) {
            return;
        }

        for (AlertModel alert : activeAlerts) {

            String userId = alert.getAudit().getCreatedBy();
            String title = "There is update on your Alert: " + alert.getAlertIdentifier();

            HashModel model = new HashModel();
            model.put("message", message);
            model.put("alertName", alert.getAlertIdentifier());

            MailNotificationModel notification = new MailNotificationModel(title,
                MailTemplateConstant.ALERT_UPDATE_TEMPLATE,
                IconType.ALERT, NotificationTypeEnum.ALERT, userId, model);

            notificationService.send(userId, notification);

        }
    }

    /**
     * @param alerts
     * @param message
     * @author Harsh Shah
     */
    private void triggerWebPush(List<AlertModel> alerts, String message) {
        if (alerts.isEmpty()) {
            return;
        }

        for (AlertModel alert : alerts) {

            String userId = alert.getAudit().getCreatedBy();

            UserDetailsModel user = userDetailsService.findUserById(userId);
            if (ApplicationConstant.SILVER.equalsIgnoreCase(user.getType())) {
                continue;
            }

            WebPushNotificationModel model = new WebPushNotificationModel(
                alert.getAudit().getCreatedBy(), alert.getAlertIdentifier(),
                message, IconType.ALERT, NotificationTypeEnum.ALERT);

            notificationService.send(userId, model);
        }
    }


    /**
     * @param alerts
     * @param message
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
     * @param alertId
     * @return
     * @author Harsh Shah
     */
    private AlertModel fetchAlertById(String alertId) {
        return alertRepository.fetchAlertById(alertId);
    }

    /**
     * @param brandId
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    private HashModel checkForAlerts(String brandId, String itemId) {
        return alertRepository.checkForAlerts(brandId, itemId);
    }

    /**
     * @param userId
     * @param alertIdentifier
     * @return
     * @author Harsh Shah
     */
    private HashModel createMailModel(String userId, String alertIdentifier) {
        UserDetailsModel user = userDetailsService.findUserById(userId);

        HashModel model = new HashModel();
        model.put("name", user.getFullName());
        model.put("alertName", alertIdentifier);
        model.put("subscriptionDate", zoneNow().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        return model;
    }
}
