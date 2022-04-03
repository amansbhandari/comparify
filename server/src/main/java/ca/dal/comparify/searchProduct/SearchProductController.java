package ca.dal.comparify.searchProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.dal.comparify.searchProduct.model.Product;
import ca.dal.comparify.searchProduct.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class SearchProductController {
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/search")
    public List<Product> getProducts(@RequestParam String name) {
        return  productRepository.getAllProducts(name);
    }

    
}
