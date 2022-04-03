package ca.dal.comparify.searchProduct;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ca.dal.comparify.searchProduct.model.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class TestSearchProductController {
    @Autowired
    private MockMvc mockMvc;
   

    @Test
    public void getProducts() throws Exception {
     
       
        this.mockMvc.perform(get("/product/search?name=Milk")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Milk")));
       this.mockMvc.perform(get("/product/search?name=sfsdf")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
     
               
               
          

    }

}
