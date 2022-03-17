package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class BrandRepository {

    public static final String BRAND_COLLECTION = "brand";

    @Autowired
    private MongoRepository mongoRepository;


    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public int save(BrandModel model){
        return mongoRepository.insertOne(BRAND_COLLECTION, model, BrandModel.class);
    }

}
