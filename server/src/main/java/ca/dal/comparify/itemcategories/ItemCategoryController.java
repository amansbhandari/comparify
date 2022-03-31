package ca.dal.comparify.itemcategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/itemcategories")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * @author Meghna Rupchandani
     */

    @GetMapping("/")
    public ArrayList<Map> getAllCategories(){
        return itemCategoryService.getAllCategories();
    }
}
