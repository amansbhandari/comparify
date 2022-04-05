package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.junit.jupiter.api.BeforeEach;
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

/**
 * aman singh bhandari
 */
@SpringBootTest
@ActiveProfiles("test")
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @MockBean
    private MongoRepository mongoRepository;

    BrandModel brandModel;

    List brandModels = new ArrayList<>();

    @BeforeEach
    void setUpTestForBrand()
    {
        brandModel = new BrandModel();
        brandModel.setDescription("dummyDescription");
        brandModel.setId("6247c13f28da7f42a60913e2");
        brandModel.setName("dummyName");

        brandModels.add(brandModel);

        when(mongoRepository.insertOne(any(),any(),any())).thenReturn(0);

        when(mongoRepository.find(any(),any(),any())).thenReturn(brandModels);

    }

    @Test
    void saveTest()
    {
        assertEquals(brandRepository.save(brandModel),0);

    }

    @Test
    void getAllTest()
    {
        assertEquals(brandRepository.getAll().size(),brandModels.size());

    }

    @Test
    void getSpecificBrandsTest()
    {
        ArrayList listStrings = new ArrayList();
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");


        assertEquals(brandRepository.getSpecificBrands(listStrings).size(),brandModels.size());

    }


    @Test
    void getBrandCountTest()
    {
        when(mongoRepository.count(any(),any())).thenReturn(new Long(20));
        assertEquals(brandRepository.getBrandCount(), new Long(20));
    }

}
