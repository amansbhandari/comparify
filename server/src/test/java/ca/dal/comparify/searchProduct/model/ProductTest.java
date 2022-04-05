package ca.dal.comparify.searchProduct.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProductTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        Product actualProduct = new Product();
        actualProduct.setBrandName("Brand Name");
        actualProduct.setDescription("The characteristics of someone or something");
        actualProduct.setImage("Image");
        actualProduct.setItemId("42");
        actualProduct.setPrice(10.0d);
        actualProduct.setProductname("Productname");
        actualProduct.setRecordId("42");
        actualProduct.setStoreName("Store Name");
        actualProduct.setUnitOrVolume(10.0d);
        assertEquals("Brand Name", actualProduct.getBrandName());
        assertEquals("The characteristics of someone or something", actualProduct.getDescription());
        assertEquals("Image", actualProduct.getImage());
        assertEquals("42", actualProduct.getItemId());
        assertEquals(10.0d, actualProduct.getPrice());
        assertEquals("Productname", actualProduct.getProductname());
        assertEquals("42", actualProduct.getRecordId());
        assertEquals("Store Name", actualProduct.getStoreName());
        assertEquals(10.0d, actualProduct.getUnitOrVolume());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        Product actualProduct = new Product("Productname", "Brand Name", "Store Name", 10.0d, 10.0d, "Image",
            "The characteristics of someone or something", "42", "42");

        assertEquals("Brand Name", actualProduct.getBrandName());
        assertEquals(10.0d, actualProduct.getUnitOrVolume());
        assertEquals("Store Name", actualProduct.getStoreName());
        assertEquals("42", actualProduct.getRecordId());
        assertEquals("Productname", actualProduct.getProductname());
        assertEquals(10.0d, actualProduct.getPrice());
        assertEquals("42", actualProduct.getItemId());
        assertEquals("Image", actualProduct.getImage());
        assertEquals("The characteristics of someone or something", actualProduct.getDescription());
    }
}

