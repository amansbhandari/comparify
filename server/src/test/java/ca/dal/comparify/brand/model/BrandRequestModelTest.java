package ca.dal.comparify.brand.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BrandRequestModelTest {
    @Test
    void testConstructor() {
        BrandRequestModel actualBrandRequestModel = new BrandRequestModel();
        actualBrandRequestModel.setDescription("The characteristics of someone or something");
        actualBrandRequestModel.setName("Name");
        assertEquals("The characteristics of someone or something", actualBrandRequestModel.getDescription());
        assertEquals("Name", actualBrandRequestModel.getName());
    }

    @Test
    void testConstructor2() {
        BrandRequestModel actualBrandRequestModel = new BrandRequestModel("Name",
            "The characteristics of someone or something");
        actualBrandRequestModel.setDescription("The characteristics of someone or something");
        actualBrandRequestModel.setName("Name");
        assertEquals("The characteristics of someone or something", actualBrandRequestModel.getDescription());
        assertEquals("Name", actualBrandRequestModel.getName());
    }
}

