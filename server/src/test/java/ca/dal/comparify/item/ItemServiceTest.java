package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.item.model.ItemRequestModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    void createTestPositive()
    {
        ItemRequestModel itemRequestModel = new ItemRequestModel();
        itemRequestModel.setItemCategoryId("6247c13f28da7f42a60913e2");
        itemRequestModel.setDefaultImage("<base64>");
        itemRequestModel.setName("dummyName");
        itemRequestModel.setDescription("dummyDescription");

        when(itemRepository.save(any())).thenReturn(0);

        assertEquals(itemService.create(itemRequestModel,"dummyCreator"),0);
    }

    @Test
    void getAllItemsTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");

        List models = new ArrayList();
        models.add(model);

        when(itemRepository.getAll()).thenReturn(models);

        assertEquals(itemService.getAllItems().get(0).get("id"),model.getId());
    }


    @Test
    void findItemPositiveTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");

        List models = new ArrayList();
        models.add(model);

        when(itemRepository.searchItemName(any())).thenReturn(model);

        assertTrue(itemService.findItem("dummyName"));
    }

    @Test
    void findItemNegativeTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");

        List models = new ArrayList();
        models.add(model);

        when(itemRepository.searchItemName(any())).thenReturn(model);

        assertTrue(itemService.findItem("dummyName"));
    }

    @Test
    void getItemDetailsTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");
        model.setId("dummyId");


        List models = new ArrayList();
        models.add(model);

        when(itemRepository.findOneItem(any())).thenReturn(model);

        assertEquals(itemService.getItemDetails("dummyId").get("name"),model.getName());
    }

}
