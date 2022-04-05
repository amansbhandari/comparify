package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import ca.dal.comparify.utils.SecurityUtils;
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
        String userId = SecurityUtils.getPrincipal(SecurityContextHolder.getContext());
        int status = alertService.create(model, userId);
        return ResponseEntityUtils.returnStatus(status);
    }

    /**
     * @return
     *
     * @author Harsh Shah
     */
    @GetMapping("")
    public List<AlertResponseModel> fetch(){
        String userId = SecurityUtils.getPrincipal(SecurityContextHolder.getContext());
        return alertService.fetch(userId);
    }


    /**
     * @param id
     * @return
     *
     * @author Harsh Shah
     */
    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> delete(@RequestParam("id") final String id){
        String userId = SecurityUtils.getPrincipal(SecurityContextHolder.getContext());
        boolean status = alertService.delete(userId, id);
        return ResponseEntityUtils.returnStatus(status ? 1 : -3);
    }

}
