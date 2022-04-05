package ca.dal.comparify.searchProduct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.searchProduct.repository.ProductRepository;
import ca.dal.comparify.store.model.StoreModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static ca.dal.comparify.mongo.MongoUtils.eq;
import static ca.dal.comparify.mongo.MongoUtils.and;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class TestProductRepository {

    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private MongoRepository mongoRepository;

    static final String STORECOLLECTION_NAME = "store";
    static final String ITEMDETAILCOLLECTION_NAME = "compareItems";
    static final String ITEMCOLLECTION_NAME = "item";
    static final String BRRANDCOLLECTION_NAME = "brand";

    private final String ITEM_ID = "productId";
    private final String STOREID = "_id";
    private final String BRANDID = "_id";
    private final String ITEMNAME = "name";

    StoreModel store = new StoreModel();
    ItemModel item = new ItemModel();
    BrandModel brand = new BrandModel();
    CompareItemsModel itemDetail = new CompareItemsModel();

    List<CompareItemsModel> itemsDetails = new ArrayList<>();

    Class<Product> productCLass = Product.class;
    Class<StoreModel> storeCLass = StoreModel.class;
    Class<ItemModel> itemCLass = ItemModel.class;
    Class<BrandModel> brandCLass = BrandModel.class;
    Class<CompareItemsModel> itemDetailCLass = CompareItemsModel.class;

    @Test
    public void getAllQuestionSuccessful() {
        ObjectId id = new ObjectId("6240ccfa09cd262cd015fdf8");
        store.setId(id);
        itemDetail.setPrice(2.0);
        itemDetail.setUnit(1.0);
        itemDetail.setStoreId(id.toString());
        itemsDetails.add(itemDetail);
        itemsDetails.add(itemDetail);
        itemDetail.setId(id);
        item.setId(id.toString());

        when(mongoRepository.findOne(ITEMCOLLECTION_NAME, eq(ITEMNAME, "Milk"), itemCLass)).thenReturn(item);
        when(mongoRepository.findOne(STORECOLLECTION_NAME, eq(STOREID,
                id), storeCLass)).thenReturn(store);
        when(mongoRepository.findOne(BRRANDCOLLECTION_NAME, eq(BRANDID, itemDetail.getBrandId()),
                brandCLass)).thenReturn(brand);
        when(mongoRepository.find(ITEMDETAILCOLLECTION_NAME,
                and(eq(ITEM_ID, item.getId()), eq("status", "verified")),
                itemDetailCLass)).thenReturn(itemsDetails);
        List<Product> products = new ArrayList<>();
        try {
            ItemModel item = mongoRepository.findOne(ITEMCOLLECTION_NAME, eq(ITEMNAME, "Milk"), itemCLass);

            List<CompareItemsModel> itemsDetails = mongoRepository.find(ITEMDETAILCOLLECTION_NAME,
                    and(eq(ITEM_ID, item.getId()), eq("status", "verified")),
                    itemDetailCLass);

            for (CompareItemsModel itemDetail : itemsDetails) {

                StoreModel store = mongoRepository.findOne(STORECOLLECTION_NAME, eq(STOREID,
                        id), storeCLass);
                String storeName = store.getStoreName();
                BrandModel brand = mongoRepository.findOne(BRRANDCOLLECTION_NAME, eq(BRANDID, itemDetail.getBrandId()),
                        brandCLass);
                String brandName = brand.getName();
                String productName = item.getName();
                double price = itemDetail.getPrice();
                double unit = itemDetail.getUnit();
                String image = item.getDefaultImage();
                String description = item.getDescription();

                String recordId = itemDetail.getId().toString();
                
                Product product = new Product(productName, brandName,storeName,  unit,price, image, description,item.getId(),recordId);

                products.add(product);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        assertEquals(productRepository.getAllProducts("Milk").size(), products.size());
    }

    @Test
    public void getAllProductsFail() {

        when(mongoRepository.find(anyString(), any(), any())).thenThrow(new NullPointerException());
        assertEquals(productRepository.getAllProducts(any()), new ArrayList<>());
    }

}