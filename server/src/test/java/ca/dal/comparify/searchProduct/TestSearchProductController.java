package ca.dal.comparify.searchProduct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.searchProduct.repository.ProductRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestSearchProductController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SearchProductController searchProductController;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductsFound() throws Exception {
        // String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1ZWM1ZTQ1Mi0wYWZmLTQyNWMtYWI5NS0yN2Y4NjIwNTQ3MDciLCJpc3MiOiJjb21wYXJpZnkiLCJleHAiOjE2NDkxMDc3NzUsImlhdCI6MTY0OTAyMTM3NX0.umP3bL0fHNlqG9S2HnxzFfeMeSkZD5j6YaMRqT1mxYc";
        String storeName = "2";
        String brandName = "3";
        String productname = "Milk";
        double price = 5;
        double unit = 6;
        String image = "7";
        String description = "8";
        String recordId = "9";
        String productId = "10";
        Product product = new Product(productname, brandName, storeName, unit, price, image, description,
                productId, recordId);

        when(productRepository.getAllProducts(any())).thenReturn(Arrays.asList(product));
        // check if the access token is valid
        this.mockMvc.perform(get("/product/search?name=Milk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productname", is("Milk")))
                .andExpect(jsonPath("$[0].brandName", is("3")))
                .andExpect(jsonPath("$[0].storeName", is("2")))
                .andExpect(jsonPath("$[0].price", is(5.0)))
                .andExpect(jsonPath("$[0].unitOrVolume", is(6.0)))
                .andExpect(jsonPath("$[0].image", is("7")))
                .andExpect(jsonPath("$[0].description", is("8")))
                .andExpect(jsonPath("$[0].itemId", is("10")))
                .andExpect(jsonPath("$[0].recordId", is("9")));
    }
   
    
   @Test
    public void getProductsNoRecordFound() throws Exception{
        // the item is not found in the database
        this.mockMvc.perform(get("/product/search?name=sfsdf"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getProductsFailBadRequest() throws Exception{
        // the item is not found in the database
        this.mockMvc.perform(get("/product/search?n=sfsdf"))
                .andExpect(status().isBadRequest());           
    }
}
