package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.model.AuditModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Meghna Rupchandani
 */

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandController brandController;

    @MockBean
    private BrandService brandService;

    @Test
    public void TestgetAllBrands() throws Exception {
        BrandModel brandModel=new BrandModel();
        brandModel.setName("dummydata");
        brandModel.setId("dummydata");
      //  brandModel.setAudit();
        brandModel.setDescription("dummydata");
        List testcontroller = new ArrayList<>();
        testcontroller.add(brandModel);

        when(brandService.getAllBrands()).thenReturn((ArrayList<Map>) testcontroller);
        this.mockMvc.perform(get("/brand/"))
                .andExpect(status().isOk());
    }

}
