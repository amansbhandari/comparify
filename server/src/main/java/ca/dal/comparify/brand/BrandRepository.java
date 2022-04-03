package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.mongo.MongoRepository;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BrandRepository {

    public static final String BRAND_COLLECTION = "brand";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param model
     * @return
     * @author Harsh Shah
     */
    public int save(BrandModel model) {
        return mongoRepository.insertOne(BRAND_COLLECTION, model, BrandModel.class);
    }

    /**
     * @return
     * 
     * @author Chanpreet Singh
     */
    public List<BrandModel> getAll(){
        List<BrandModel> result = mongoRepository.find(BRAND_COLLECTION, Filters.empty(), BrandModel.class);
        return result;
    }

    /**
     * @return
     *
     * @author Chanpreet Singh
     */
    public List<BrandModel> getSpecificBrands(ArrayList<String> brandList){
        List<String> brandListUnique = (List<String>) brandList.stream().distinct().collect(Collectors.toList());
        List<BrandModel> result = mongoRepository.find(BRAND_COLLECTION, Filters.in("_id", brandListUnique), BrandModel.class);
        return result;
    }

    /**
     * @author Chanpreet Singh
     */
    public Long getBrandCount(){
        return mongoRepository.count(BRAND_COLLECTION, Filters.empty());
    }
}