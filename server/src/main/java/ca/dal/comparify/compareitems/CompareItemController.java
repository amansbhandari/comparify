package ca.dal.comparify.compareitems;

import ca.dal.comparify.Appreciation.AppreciationService;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Chanpreet Singh
 */
@RestController
@RequestMapping("/compareitems")
public class CompareItemController {

    @Autowired
    private CompareItemService compareItemService;

    @Autowired
    private AppreciationService appreciationService;

    /**
     * @param model
     * @return
     * @author Chanpreet Singh
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> create(@RequestBody CompareItemsModel model) {
        appreciationService.addAppreciation(model);

        int status = compareItemService.create(model);
        return ResponseEntityUtils.returnStatus(status);
    }

    @GetMapping("/")
    public ArrayList<Map<String, Object>> getComparisions(@RequestParam(name = "itemId") String ItemId, @RequestParam(name = "date", required = false) String date){
        return compareItemService.fetchComparisions(ItemId, date);
    }
}