package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Harsh Shah
 */
@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    @PostMapping("")
    public ResponseEntity<Map<String, String>> create(@RequestBody AlertRequestModel model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int status = alertService.create(model, (String) auth.getPrincipal());

        return ResponseEntityUtils.returnStatus(status);
    }

    /**
     * @return
     *
     * @author Harsh Shah
     */
    @GetMapping("")
    public List<AlertResponseModel> fetch(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return alertService.fetch((String) auth.getPrincipal());
    }


}
