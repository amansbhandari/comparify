package ca.dal.comparify.itemcategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/itemcategories")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * @author Meghna Rupchandani
     * Sonar fix by Harsh Shah
     */

    @GetMapping("/")
    public List<Map<String, String>> getAllCategories(){
        return itemCategoryService.getAllCategories();
    }
}
