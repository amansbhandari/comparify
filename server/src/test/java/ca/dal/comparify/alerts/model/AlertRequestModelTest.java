package ca.dal.comparify.alerts.model;

import ca.dal.comparify.model.EntityReference;
import ca.dal.comparify.model.RangeModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class AlertRequestModelTest {

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        AlertRequestModel actualAlertRequestModel = new AlertRequestModel();
        actualAlertRequestModel.setAlertIdentifier("42");
        EntityReference entityReference = new EntityReference("42", "Entity Type");

        actualAlertRequestModel.setBrand(entityReference);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertRequestModel.setExpiresOn(fromResult);
        EntityReference entityReference1 = new EntityReference("42", "Entity Type");

        actualAlertRequestModel.setItem(entityReference1);
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertRequestModel.setPriceRange(rangeModel);
        actualAlertRequestModel.setType(AlertTypeEnum.PRICE_DROP);
        assertEquals("42", actualAlertRequestModel.getAlertIdentifier());
        assertSame(entityReference, actualAlertRequestModel.getBrand());
        assertSame(fromResult, actualAlertRequestModel.getExpiresOn());
        assertSame(entityReference1, actualAlertRequestModel.getItem());
        assertSame(rangeModel, actualAlertRequestModel.getPriceRange());
        assertEquals(AlertTypeEnum.PRICE_DROP, actualAlertRequestModel.getType());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        EntityReference item = new EntityReference("42", "Entity Type");

        AlertRequestModel actualAlertRequestModel = new AlertRequestModel(item, new EntityReference("42", "Entity Type"),
            AlertTypeEnum.PRICE_DROP);
        actualAlertRequestModel.setAlertIdentifier("42");
        EntityReference entityReference = new EntityReference("42", "Entity Type");

        actualAlertRequestModel.setBrand(entityReference);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertRequestModel.setExpiresOn(fromResult);
        EntityReference entityReference1 = new EntityReference("42", "Entity Type");

        actualAlertRequestModel.setItem(entityReference1);
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertRequestModel.setPriceRange(rangeModel);
        actualAlertRequestModel.setType(AlertTypeEnum.PRICE_DROP);
        assertEquals("42", actualAlertRequestModel.getAlertIdentifier());
        assertSame(entityReference, actualAlertRequestModel.getBrand());
        assertSame(fromResult, actualAlertRequestModel.getExpiresOn());
        assertSame(entityReference1, actualAlertRequestModel.getItem());
        assertSame(rangeModel, actualAlertRequestModel.getPriceRange());
        assertEquals(AlertTypeEnum.PRICE_DROP, actualAlertRequestModel.getType());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        EntityReference item = new EntityReference("42", "Entity Type");

        EntityReference brand = new EntityReference("42", "Entity Type");

        RangeModel<Integer> priceRange = new RangeModel<>();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        AlertRequestModel actualAlertRequestModel = new AlertRequestModel("42", item, brand, AlertTypeEnum.PRICE_DROP,
            priceRange, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        actualAlertRequestModel.setAlertIdentifier("42");
        EntityReference entityReference = new EntityReference("42", "Entity Type");

        actualAlertRequestModel.setBrand(entityReference);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertRequestModel.setExpiresOn(fromResult);
        EntityReference entityReference1 = new EntityReference("42", "Entity Type");

        actualAlertRequestModel.setItem(entityReference1);
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertRequestModel.setPriceRange(rangeModel);
        actualAlertRequestModel.setType(AlertTypeEnum.PRICE_DROP);
        assertEquals("42", actualAlertRequestModel.getAlertIdentifier());
        assertSame(entityReference, actualAlertRequestModel.getBrand());
        assertSame(fromResult, actualAlertRequestModel.getExpiresOn());
        assertSame(entityReference1, actualAlertRequestModel.getItem());
        assertSame(rangeModel, actualAlertRequestModel.getPriceRange());
        assertEquals(AlertTypeEnum.PRICE_DROP, actualAlertRequestModel.getType());
    }
}

