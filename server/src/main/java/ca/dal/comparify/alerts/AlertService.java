package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public int create(AlertRequestModel model, String createdBy){
        return alertRepository.save(AlertModel.transform(model, createdBy));
    }
}
