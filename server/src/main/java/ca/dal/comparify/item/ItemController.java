package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemRequestModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Chanpreet Singh
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * @param model
     * @return
     * @author Chanpreet Singh
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> create(@RequestBody ItemRequestModel model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int status = itemService.create(model, (String) auth.getPrincipal());

        return ResponseEntityUtils.returnStatus(status);
    }

    /**
     * @author Chanpreet Singh
     * Sonar fix by Harsh Shah
     */
    @GetMapping("/")
    public List<Map<String, String>> getItems(){
        return itemService.getAllItems();
    }

}
