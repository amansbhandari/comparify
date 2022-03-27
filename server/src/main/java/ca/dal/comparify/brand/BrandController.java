package ca.dal.comparify.brand;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.brand.model.BrandRequestModel;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.ArrayList;

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
     */

    @GetMapping("/")
    public ArrayList<Map> getAllBrands(){
        return brandService.getAllBrands();
    }
}
