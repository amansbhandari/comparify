package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.brand.model.BrandRequestModel;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * aman singh bhandari
 */
@SpringBootTest
@ActiveProfiles("test")
public class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    @MockBean
    private BrandRepository brandRepository;


    @Test
    void testCreate()
    {
        BrandRequestModel brandRequestModel = new BrandRequestModel();
        brandRequestModel.setDescription("dummyDescription");
        brandRequestModel.setName("dummyName");

        when(brandRepository.save(any())).thenReturn(0);
        assertEquals(brandService.create(brandRequestModel,"dummyCreatedBy"),0);
    }

    @Test
    void getAllBrandsTest()
    {
        BrandModel brandModel = new BrandModel();
        brandModel.setDescription("dummyDescription");
        brandModel.setId("6247c13f28da7f42a60913e2");
        brandModel.setName("dummyName");

        List brandModels = new ArrayList<>();
        brandModels.add(brandModel);

        when(brandRepository.getAll()).thenReturn(brandModels);
        assertEquals(brandService.getAllBrands().get(0).get("name"),brandModel.getName());
    }


    @Test
    void getSpecificBrandDetailsTest()
    {

        BrandModel brandModel = new BrandModel();
        brandModel.setDescription("dummyDescription");
        brandModel.setId("6247c13f28da7f42a60913e2");
        brandModel.setName("dummyName");

        List brandModels = new ArrayList<>();
        brandModels.add(brandModel);

        when(brandRepository.getSpecificBrands(any())).thenReturn(brandModels);

        ArrayList<String> list = new ArrayList<>();
        list.add("6247c13f28da7f42a60913e2");
        list.add("6247c13f28da7f42a60913e2");

        HashMap<String, String> map = (HashMap<String, String>) brandService.getSpecificBrandDetails(list).get("6247c13f28da7f42a60913e2");
        assertEquals(map.get("name"),brandModel.getName());
    }
}
