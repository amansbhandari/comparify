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

//    @GetMapping("/trigger")
//    public void trigger(){
//        alertService.trigger("84e218d4-2c57-47a1-85b1-91f1b201a437", "588fa189-7ed1-41c0-8cc7-eac61304e7b9");
//    }

}
