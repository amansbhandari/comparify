package ca.dal.comparify.ItemCategoryTest;

import ca.dal.comparify.item.model.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.dal.comparify.itemcategories.ItemCategoryController;
import ca.dal.comparify.itemcategories.ItemCategoryService;
import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Meghna Rupchandani
 */


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ItemCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemCategoryController itemCategoryController;

    @MockBean
    private ItemCategoryService itemCategoryService;



    @Test
    void TestGetAllCategories() throws Exception {
        when(itemCategoryController.getAllCategories()).thenReturn(emptyList());
        this.mockMvc.perform(get("/itemcategories/"))
                .andExpect(status().isOk());
    }
}
