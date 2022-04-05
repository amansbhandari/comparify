package ca.dal.comparify.compareItems;

import ca.dal.comparify.alerts.AlertService;
import ca.dal.comparify.brand.BrandService;
import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.CompareItemService;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.item.ItemService;
import ca.dal.comparify.item.model.ItemRequestModel;
import ca.dal.comparify.itemcategories.ItemCategoryService;
import ca.dal.comparify.store.StoreService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class CompareItemsServiceTest {
    @MockBean
    private CompareItemRepository compareItemRepository;
    @MockBean
    private BrandService brandService;
    @MockBean
    private StoreService storeService;
    @MockBean
    private AlertService alertService;
    @MockBean
    private ItemService itemService;
    @MockBean
    private ItemCategoryService itemCategoryService;
    @Autowired
    private CompareItemService compareItemService;

    @Test
    void createTestPositive()
    {
        CompareItemsModel compareItemsModel = new CompareItemsModel();
        compareItemsModel.setId(new ObjectId("6244e035d705d40bc66c43b0"));
        compareItemsModel.setDateOfPurchase(new Date());
        compareItemsModel.setBrandId("6244e035d705d40bc66c43b0");
        compareItemsModel.setProductId("6244e035d705d40bc66c43b0");
        compareItemsModel.setImageText("6244e035d705d40bc66c43b0");
        compareItemsModel.setPrice(200.0);
        compareItemsModel.setPriceAfterDiscount(190.06);
        compareItemsModel.setStoreId("6244e035d705d40bc66c43b0");
        compareItemsModel.setUnit(2.9);
        compareItemsModel.setUserId("6244e035d705d40bc66c43b0");
        compareItemsModel.setStatus("verified");

        when(compareItemRepository.save(any())).thenReturn(0);
        assertEquals(compareItemService.create(compareItemsModel),0);
    }
}
