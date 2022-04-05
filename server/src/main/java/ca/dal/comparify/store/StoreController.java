package ca.dal.comparify.store;

import ca.dal.comparify.store.model.StoreModel;
import ca.dal.comparify.user.model.iam.UserDetailsRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * @author Meghna Rupchandani
     * Sonar fix by Harsh Shah
     */
    @GetMapping("/")
    public List<Map<String, String>> getAllStores(){
        return storeService.getAllStores();
    }


    /**
     * @author amansbhandari
     */
    @PostMapping("/")
    public String saveStore(@RequestBody StoreModel storeModel)
    {
        return storeService.saveStore(storeModel);
    }
}
