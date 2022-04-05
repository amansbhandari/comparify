package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandRequestModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @param model
     * @return
     * @author Harsh Shah
     */

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> create(@RequestBody BrandRequestModel model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        int status = brandService.create(model, (String) auth.getPrincipal());
        return ResponseEntityUtils.returnStatus(status);
    }

    /**
     * @author Chanpreet Singh
     * Sonar fix by Harsh Shah
     */
    @GetMapping("/")
    public List<Map<String, String>> getAllBrands() {
        return brandService.getAllBrands();
    }
}
