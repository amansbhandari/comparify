package ca.dal.comparify.analytics;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.*;
import static java.util.Arrays.asList;

/**
 * @author Harsh Shah
 */
@Service
public class AnalyticsRepository {

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param itemId
     * @return
     *
     * @author Harsh Shah
     */
    public List<HashModel> getPriceTrend(String itemId) {

        List<Bson> pipeline = asList(
            match(new Document("status", "verified")
                .append("productId", itemId)),

            sortAscending("dateOfPurchase"),

            group(new Document("_id", "$dateOfPurchase")
                .append("price",
                    new Document("$first", "$price"))
                .append("brands",
                    new Document("$addToSet", "$brandId"))
                .append("stores",
                    new Document("$addToSet", "$storeId"))),

            lookup("brand",
                new Document("category", "$brands"),
                asList(match(new Document("$expr", new Document("$in", asList("$_id", "$$category")))),
                    project(new Document("name", "$name"))),
                "brands"),


            lookup("store",
                new Document("category",
                    map("$stores", "itr", new Document("$toObjectId", "$$itr"))),
                asList(
                    match(new Document("$expr",
                        new Document("$in", asList("$_id", "$$category")))),
                    project(new Document("name", "$storeName"))),
                "stores"));

        return mongoRepository.aggregate(CompareItemRepository.ITEM_COLLECTION, pipeline,
            HashModel.class);
    }

    /**
     * @param itemId
     * @return
     *
     * @author Harsh Shah
     */
    public HashModel getPriceTrendForDifferentBrands(String itemId) {

        List<Bson> pipeline = asList(

            match(new Document("status", "verified")
                .append("productId", itemId)),


            sortAscending("dateOfPurchase"),

            facet(new Document("brands", asList(

                group(new Document("_id", "$brandId"))))
                .append("trend", asList(

                    group(new Document("_id",
                        new Document("date", "$dateOfPurchase")
                            .append("brand", "$brandId"))
                        .append("price",
                            first("$price")))))));


        return mongoRepository.aggregateOne(CompareItemRepository.ITEM_COLLECTION, pipeline,
            HashModel.class);
    }

    /**
     * @param brands
     * @return
     *
     * @author Harsh Shah
     */
    public List<BrandModel> getBrands(List<String> brands) {
         return mongoRepository.find("brand",
            eq("_id",  new Document("$in", brands)),eq("name", 1), BrandModel.class);
    }
}
