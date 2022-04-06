package ca.dal.comparify.analytics;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.*;
import static java.util.Arrays.asList;

/**
 * @author Harsh Shah
 */
@Service
public class AnalyticsRepository {

    private static final String STATUS = "status";
    private static final String VERIFIED = "verified";
    private static final String DATE_OF_PURCHASE = "$dateOfPurchase";
    private static final String PRICE = "$price";
    private static final String BRANDS = "brands";
    private static final String BRAND_ID = "$brandId";
    private static final String BRAND = "brand";
    private static final String CATEGORY = "category";
    private static final String EXPR = "$expr";



    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    public List<HashModel> getPriceTrend(String itemId) {

        List<Bson> pipeline = asList(
            match(new Document(STATUS, VERIFIED)
                .append("productId", itemId)),

            sortAscending("dateOfPurchase"),

            group(new Document("_id", DATE_OF_PURCHASE)
                .append("price",
                    new Document("$first", PRICE))
                .append(BRANDS,
                    new Document("$addToSet", BRAND_ID))
                .append("stores",
                    new Document("$addToSet", "$storeId"))),

            lookup(BRAND,
                new Document(CATEGORY, "$brands"),
                asList(match(new Document(EXPR, new Document("$in", asList("$_id", "$$category")))),
                    project(new Document("name", "$name"))),
                BRANDS),


            lookup("store",
                new Document(CATEGORY,
                    map("$stores", "itr", new Document("$toObjectId", "$$itr"))),
                asList(
                    match(new Document(EXPR,
                        new Document("$in", asList("$_id", "$$category")))),
                    project(new Document("name", "$storeName"))),
                "stores"));

        return mongoRepository.aggregate(CompareItemRepository.ITEM_COLLECTION, pipeline,
            HashModel.class);
    }

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    public HashModel getPriceTrendForDifferentBrands(String itemId) {

        List<Bson> pipeline = asList(

            match(new Document(STATUS, VERIFIED)
                .append("productId", itemId)),

            facet(new Document(BRANDS, asList(

                group(new Document("_id", BRAND_ID))))
                .append("trend", asList(

                    group(new Document("_id",
                        new Document("date", DATE_OF_PURCHASE)
                            .append(BRAND, BRAND_ID))
                        .append("price",
                            first(PRICE))),
                    sortAscending("_id.date")
                ))));


        return mongoRepository.aggregateOne(CompareItemRepository.ITEM_COLLECTION, pipeline,
            HashModel.class);
    }

    /**
     * @param brands
     * @return
     * @author Harsh Shah
     */
    public List<BrandModel> getBrands(List<String> brands) {
        return mongoRepository.find(BRAND,
            eq("_id", new Document("$in", brands)), eq("name", 1), BrandModel.class);
    }


    /**
     * @param date
     * @return
     * @author Harsh Shah
     */
    public List<HashModel> getProductCountForCategory(LocalDate date) {
        List<Bson> pipeline = asList(
            match(new Document(STATUS, VERIFIED)
                .append("dateOfPurchase", date)),

            group(new Document("_id", "$productId")
                .append("count", new Document("$sum", 1L))),

            lookup("item", "_id", "_id", "item"),

            unwind("$item"),

            lookup("itemCategories",
                new Document("categoryId",
                    new Document("$toObjectId", "$item.itemCategoryId")),
                asList(match(new Document(EXPR,
                    new Document("$eq", asList("$_id", "$$categoryId"))))),
                CATEGORY),
            unwind("$category"),
            project(new Document("count", 1L)
                .append(CATEGORY, "$category.categoryName")));

        return mongoRepository.aggregate(CompareItemRepository.ITEM_COLLECTION, pipeline,
            HashModel.class);
    }


    /**
     * @param month
     * @return
     * @author Harsh Shah
     */
    public List<HashModel> getMonthlyTotalPurchaseOfItemCategory(int month) {
        List<Bson> pipeline = asList(

            match(new Document(STATUS, VERIFIED)
                .append(EXPR,
                    new Document("$eq", asList(new Document("$month", DATE_OF_PURCHASE), month)))),

            group(
                new Document("_id",
                    new Document("date", DATE_OF_PURCHASE)
                        .append("itemId", "$productId"))
                    .append("totalPurchase",
                        new Document("$sum", PRICE))),

            lookup("item", "_id.itemId", "_id", "item"),

            unwind("$item"),

            group(new Document("_id", "$item.itemCategoryId")
                .append("totalPurchase",
                    new Document("$sum", "$totalPurchase"))));

        return mongoRepository.aggregate(CompareItemRepository.ITEM_COLLECTION,
            pipeline, HashModel.class);
    }

    /**
     * @return
     * @author Harsh Shah
     */
    public List<ItemCategoryModel> getAllCategories() {
        return mongoRepository.find("itemCategories", new Document(), ItemCategoryModel.class);
    }
}
