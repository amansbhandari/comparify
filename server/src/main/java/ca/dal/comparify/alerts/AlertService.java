package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.alerts.model.AlertTypeEnum;
import ca.dal.comparify.notification.NotificationService;
import ca.dal.comparify.notification.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public int create(AlertRequestModel model, String createdBy){
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
//    public void trigger(String brandId, String productId){
//
//        List<AlertModel> alerts = checkForAlerts(brandId, productId);
//
//        List<AlertModel> alertsOnInformationAvailable = alerts.stream().
//            filter(alert -> AlertTypeEnum.PRODUCT_INFORMATION_AVAILABLE.equals(alert.getType()))
//            .collect(Collectors.toList());
//
//        if(!alertsOnInformationAvailable.isEmpty()){
//
//            AlertModel alert = alertsOnInformationAvailable.get(0);
//
////            WebPushNotificationModel model = new WebPushNotificationModel(
////                alert.getAudit().getCreatedBy(),
////                alert.getAlertIdentifier(),
////                "Product Information Available", IconType.ALERT, NotificationTypeEnum.ALERT);
//
//            WebSocketNotificationModel model = new WebSocketNotificationModel(
//                Collections.singletonList(alert.getAudit().getCreatedBy()),
//                alert.getAlertIdentifier(),
//                "Product Information Available", IconType.ALERT, NotificationTypeEnum.ALERT);
//
//            notificationService.send(null,
//                NotificationChannelType.SOCKET, model);
//
////            notificationService.send(alertsOnInformationAvailable.get(0).getAudit().getCreatedBy(),
////                NotificationChannelType.SOCKET, null);
//        }
//    }


    /**
     * @param brandId
     * @param productId
     * @return
     *
     * @author Harsh Shah
     */
    private List<AlertModel> checkForAlerts(String brandId, String productId){
        return alertRepository.checkForAlerts(brandId, productId);
    }
}
