package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @MockBean
    private MongoRepository mongoRepository;

    @Test
    void saveTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");

        when(mongoRepository.insertOne(any(), any(), any())).thenReturn(0);

        assertEquals(itemRepository.save(model),0);
    }

    @Test
    void getAllTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");

        List models = new ArrayList();
        models.add(model);

        when(mongoRepository.find(any(), any(), any())).thenReturn(models);

        assertEquals(itemRepository.getAll().size(),models.size());
    }


    @Test
    void searchItemNameTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");

        List models = new ArrayList();
        models.add(model);

        when(mongoRepository.findOne(any(), any(), any())).thenReturn(model);

        assertEquals(itemRepository.searchItemName("dummyName").getName(),model.getName());
    }

    @Test
    void findOneItemTest()
    {
        ItemModel model = new ItemModel();
        model.setDefaultImage("<base64>");
        model.setDescription("dummyDescription");
        model.setItemCategoryId("6247c13f28da7f42a60913e2");
        model.setName("dummyName");
        model.setId("dummyId");


        List models = new ArrayList();
        models.add(model);

        when(mongoRepository.findOne(any(), any(), any())).thenReturn(model);

        assertEquals(itemRepository.findOneItem("dummyId").getId(),model.getId());
    }

    @Test
    void getItemCountTest()
    {
        when(mongoRepository.count(any(), any())).thenReturn(new Long(20));
        assertEquals(itemRepository.getItemCount(),new Long(20));
    }
}
