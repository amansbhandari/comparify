package ca.dal.comparify.ItemCategoryTest;


import ca.dal.comparify.itemcategories.ItemCategoryRepository;
import ca.dal.comparify.itemcategories.ItemCategoryService;
import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * @author Meghna Rupchandani
*/

@SpringBootTest
@ActiveProfiles("test")
public class ItemCategoryServiceTest {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @MockBean
    private ItemCategoryRepository itemCategoryRepository;


    @Test
    void TestgetAllCategories(){

        ObjectId id = new ObjectId();
        ItemCategoryModel itemCategoryModel =new ItemCategoryModel();
        itemCategoryModel.setCategoryName("dummyname");
        itemCategoryModel.setId(id);

        List categorymodels = new ArrayList<>();
        categorymodels.add(itemCategoryModel);

        when(itemCategoryRepository.getAllCategories()).thenReturn(categorymodels);

        assertEquals(itemCategoryService.getAllCategories().size(),categorymodels.size());
    }


    @Test
    void TestgetItemCategory(){


        ObjectId id=new ObjectId();
        ItemCategoryModel itemCategoryModel=new ItemCategoryModel();
        itemCategoryModel.setId(id);
        itemCategoryModel.setCategoryName("dummydata");

        List models = new ArrayList();
        models.add(itemCategoryModel);


        when(itemCategoryRepository.getCategoryName(any())).thenReturn(itemCategoryModel);
        assertEquals(itemCategoryService.getItemCategory("dummydata").get("categoryName"),itemCategoryModel.getCategoryName());


    }

    }

