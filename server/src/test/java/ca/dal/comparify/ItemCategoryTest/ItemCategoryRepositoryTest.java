package ca.dal.comparify.ItemCategoryTest;

import ca.dal.comparify.itemcategories.ItemCategoryRepository;
import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import ca.dal.comparify.mongo.MongoRepository;
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

@SpringBootTest
@ActiveProfiles("test")

/**
 * @author Meghna Rupchandani
 */

public class ItemCategoryRepositoryTest {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @MockBean
    private MongoRepository mongoRepository;


    @Test
    void TestgetAll(){

        ObjectId id=null;
        ItemCategoryModel itemCategoryModel=new ItemCategoryModel();
        itemCategoryModel.setCategoryName("dummydata");
        itemCategoryModel.setId(id);

        List categorymodels = new ArrayList<>();
        categorymodels.add(itemCategoryModel);

        when(mongoRepository.find(any(),any(),any())).thenReturn(categorymodels);
        assertEquals(itemCategoryRepository.getAllCategories().size(), categorymodels.size());
    }


    @Test
    void TestgetCategoryName(){

        ItemCategoryModel itemCategoryModel=new ItemCategoryModel();
        itemCategoryModel.setId(new ObjectId("6247c13f28da7f42a60913e2"));
        itemCategoryModel.setCategoryName("dummydata");

        List models = new ArrayList();
        models.add(itemCategoryModel);

        when(mongoRepository.findOne(any(),any(),any())).thenReturn(itemCategoryModel);
        assertEquals(itemCategoryRepository.getCategoryName("6247c13f28da7f42a60913e2").getId(), itemCategoryModel.getId());

    }

}

