package ca.dal.comparify.searchProduct.repository;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.store.model.StoreModel;


import java.util.ArrayList;
import java.util.List;
import static ca.dal.comparify.mongo.MongoUtils.eq;

import static ca.dal.comparify.mongo.MongoUtils.and;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRepository {

    @Autowired
    private MongoRepository mongoRepository;
    static final String STORECOLLECTION_NAME = "store";
    static final String ITEMDETAILCOLLECTION_NAME = "compareItems";
    static final String ITEMCOLLECTION_NAME = "item";
    static final String BRRANDCOLLECTION_NAME = "brand";

    private final String itemId = "productId";
    private final String storeid = "_id";
    private final String brandid = "_id";
    private final String itemname = "name";

    Class<Product> productCLass = Product.class;
    Class<StoreModel> storeCLass = StoreModel.class;
    Class<ItemModel> itemCLass = ItemModel.class;
    Class<BrandModel> brandCLass = BrandModel.class;
    Class<CompareItemsModel> itemDetailCLass = CompareItemsModel.class;

    public List<Product> getAllProducts(String itemName) {
        List<Product> products = new ArrayList<>();
        try {
            ItemModel item = mongoRepository.findOne(ITEMCOLLECTION_NAME, eq(itemname, itemName), itemCLass);

            List<CompareItemsModel> itemsDetails = mongoRepository.find(ITEMDETAILCOLLECTION_NAME,
                    and(eq(itemId, item.getId()), eq("status", "verified")),
                    itemDetailCLass);

            for (CompareItemsModel itemDetail : itemsDetails) {

                StoreModel store = mongoRepository.findOne(STORECOLLECTION_NAME, eq(storeid,
                        new ObjectId(itemDetail.getStoreId())),
                        storeCLass);
                String storeName = store.getStoreName();
                BrandModel brand = mongoRepository.findOne(BRRANDCOLLECTION_NAME, eq(brandid, itemDetail.getBrandId()),
                        brandCLass);
                String brandName = brand.getName();
                String productName = item.getName();
                double price = itemDetail.getPrice();
                double unit = itemDetail.getUnit();
                String image = item.getDefaultImage();
                String description = item.getDescription();

                String recordId = itemDetail.getId().toString();
                Product p = new Product(productName, brandName, storeName, price, unit, image, description,
                    item.getId(),  recordId);
                products.add(p);
            }

        } catch (Exception e) {  
            return products;
        }
      
       
        return products;
    }

}
