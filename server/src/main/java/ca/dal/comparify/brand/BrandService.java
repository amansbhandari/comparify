package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.brand.model.BrandRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public int create(BrandRequestModel model, String createdBy){
        return brandRepository.save(BrandModel.transform(model, createdBy));
    }

}
