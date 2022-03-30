package ca.dal.comparify.compareitems;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Chanpreet Singh
 */
@RestController
@RequestMapping("/compareitems")
public class CompareItemController {

    @Autowired
    private CompareItemService compareItemService;

    /**
     * @param model
     * @return
     * @author Chanpreet Singh
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> create(@RequestBody CompareItemsModel model) {
        int status = compareItemService.create(model);
        return ResponseEntityUtils.returnStatus(status);
    }
}