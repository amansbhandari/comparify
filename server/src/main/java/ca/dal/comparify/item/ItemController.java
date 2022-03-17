package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemRequestModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Harsh Shah
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * @param model
     * @return
     * @author Harsh Shah
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> create(@RequestBody ItemRequestModel model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int status = itemService.create(model, (String) auth.getPrincipal());

        return ResponseEntityUtils.returnStatus(status);
    }
}
