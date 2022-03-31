package ca.dal.comparify.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * @author Meghna Rupchandani
     */

    @GetMapping("/")
    public ArrayList<Map> getAllStores(){
        return storeService.getAllStores();
    }
}
