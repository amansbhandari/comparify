package ca.dal.comparify.compareItems.model;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CompareItemsModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        CompareItemsModel actualCompareItemsModel = new CompareItemsModel();
        actualCompareItemsModel.setBrandId("42");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualCompareItemsModel.setDateOfPurchase(fromResult);
        ObjectId getResult = ObjectId.get();
        actualCompareItemsModel.setId(getResult);
        actualCompareItemsModel.setImageText("Image Text");
        actualCompareItemsModel.setPrice(10.0d);
        actualCompareItemsModel.setPriceAfterDiscount(10.0d);
        actualCompareItemsModel.setProductId("42");
        actualCompareItemsModel.setStatus("Status");
        actualCompareItemsModel.setStoreId("42");
        actualCompareItemsModel.setUnit(10.0d);
        actualCompareItemsModel.setUserId("42");
        assertEquals("42", actualCompareItemsModel.getBrandId());
        assertSame(fromResult, actualCompareItemsModel.getDateOfPurchase());
        assertSame(getResult, actualCompareItemsModel.getId());
        assertEquals("Image Text", actualCompareItemsModel.getImageText());
        assertEquals(10.0d, actualCompareItemsModel.getPrice().doubleValue());
        assertEquals(10.0d, actualCompareItemsModel.getPriceAfterDiscount().doubleValue());
        assertEquals("42", actualCompareItemsModel.getProductId());
        assertEquals("Status", actualCompareItemsModel.getStatus());
        assertEquals("42", actualCompareItemsModel.getStoreId());
        assertEquals(10.0d, actualCompareItemsModel.getUnit().doubleValue());
        assertEquals("42", actualCompareItemsModel.getUserId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        ObjectId id = ObjectId.get();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        CompareItemsModel actualCompareItemsModel = new CompareItemsModel(id, "Image Text", "42", "42", 10.0d, 10.0d, "42",
            Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), 10.0d, "42", "Status");
        actualCompareItemsModel.setBrandId("42");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualCompareItemsModel.setDateOfPurchase(fromResult);
        ObjectId getResult = ObjectId.get();
        actualCompareItemsModel.setId(getResult);
        actualCompareItemsModel.setImageText("Image Text");
        actualCompareItemsModel.setPrice(10.0d);
        actualCompareItemsModel.setPriceAfterDiscount(10.0d);
        actualCompareItemsModel.setProductId("42");
        actualCompareItemsModel.setStatus("Status");
        actualCompareItemsModel.setStoreId("42");
        actualCompareItemsModel.setUnit(10.0d);
        actualCompareItemsModel.setUserId("42");
        assertEquals("42", actualCompareItemsModel.getBrandId());
        assertSame(fromResult, actualCompareItemsModel.getDateOfPurchase());
        assertSame(getResult, actualCompareItemsModel.getId());
        assertEquals("Image Text", actualCompareItemsModel.getImageText());
        assertEquals(10.0d, actualCompareItemsModel.getPrice().doubleValue());
        assertEquals(10.0d, actualCompareItemsModel.getPriceAfterDiscount().doubleValue());
        assertEquals("42", actualCompareItemsModel.getProductId());
        assertEquals("Status", actualCompareItemsModel.getStatus());
        assertEquals("42", actualCompareItemsModel.getStoreId());
        assertEquals(10.0d, actualCompareItemsModel.getUnit().doubleValue());
        assertEquals("42", actualCompareItemsModel.getUserId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date actualDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        CompareItemsModel actualCompareItemsModel = new CompareItemsModel("Image Text", "42", "42", 10.0d, 10.0d, "42",
            actualDate, 10.0d, "42", "Status");
        assertEquals("42", actualCompareItemsModel.getBrandId());
        assertEquals("42", actualCompareItemsModel.getUserId());
        assertEquals(10.0d, actualCompareItemsModel.getUnit().doubleValue());
        assertEquals("42", actualCompareItemsModel.getStoreId());
        assertEquals("Status", actualCompareItemsModel.getStatus());
        assertEquals("42", actualCompareItemsModel.getProductId());
        assertEquals(10.0d, actualCompareItemsModel.getPriceAfterDiscount().doubleValue());
        assertEquals(10.0d, actualCompareItemsModel.getPrice().doubleValue());
        assertEquals("Image Text", actualCompareItemsModel.getImageText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertSame(actualDate, actualCompareItemsModel.getDateOfPurchase());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate() {
        CompareItemsModel actualCreateResult = CompareItemsModel.create(new CompareItemsModel());
        assertNull(actualCreateResult.getBrandId());
        assertNull(actualCreateResult.getUserId());
        assertNull(actualCreateResult.getUnit());
        assertNull(actualCreateResult.getStoreId());
        assertNull(actualCreateResult.getStatus());
        assertNull(actualCreateResult.getProductId());
        assertNull(actualCreateResult.getPriceAfterDiscount());
        assertNull(actualCreateResult.getPrice());
        assertNull(actualCreateResult.getImageText());
        assertNull(actualCreateResult.getDateOfPurchase());
    }
}

