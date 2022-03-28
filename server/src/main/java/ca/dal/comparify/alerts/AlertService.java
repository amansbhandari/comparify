package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Harsh Shah
 */
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

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
}
