package ca.dal.comparify.item;



import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.item.model.ItemRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ItemController itemController;

    @MockBean
    ItemService itemService;

    @Test
    public void TestgetItems() throws Exception{

        ItemModel model=new ItemModel();
        model.setName("dummydata");
        model.setId("dummydata");
        model.setDescription("dummydata");
        model.setDefaultImage("dummydata");
        //model.setAudit();

        List testcontroller = new ArrayList<>();
        testcontroller.add(model);

        when(itemController.getItems()).thenReturn((ArrayList<Map>) testcontroller);
        this.mockMvc.perform(get("/item/"))
                .andExpect(status().isOk());

    }


}





