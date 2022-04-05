package ca.dal.comparify.alerts.model;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.model.RangeModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class AlertResponseModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        AlertResponseModel actualAlertResponseModel = new AlertResponseModel();
        actualAlertResponseModel.setAlertIdentifier("42");
        BrandModel brandModel = new BrandModel();
        actualAlertResponseModel.setBrand(brandModel);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertResponseModel.setExpiresOn(fromResult);
        actualAlertResponseModel.setId("42");
        ItemModel itemModel = new ItemModel();
        actualAlertResponseModel.setItem(itemModel);
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertResponseModel.setPriceRange(rangeModel);
        actualAlertResponseModel.setType(AlertTypeEnum.PRICE_DROP);
        assertEquals("42", actualAlertResponseModel.getAlertIdentifier());
        assertSame(brandModel, actualAlertResponseModel.getBrand());
        assertSame(fromResult, actualAlertResponseModel.getExpiresOn());
        assertEquals("42", actualAlertResponseModel.getId());
        assertSame(itemModel, actualAlertResponseModel.getItem());
        assertSame(rangeModel, actualAlertResponseModel.getPriceRange());
        assertEquals(AlertTypeEnum.PRICE_DROP, actualAlertResponseModel.getType());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        ItemModel item = new ItemModel();
        AlertResponseModel actualAlertResponseModel = new AlertResponseModel(item, new BrandModel(),
            AlertTypeEnum.PRICE_DROP);
        actualAlertResponseModel.setAlertIdentifier("42");
        BrandModel brandModel = new BrandModel();
        actualAlertResponseModel.setBrand(brandModel);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertResponseModel.setExpiresOn(fromResult);
        actualAlertResponseModel.setId("42");
        ItemModel itemModel = new ItemModel();
        actualAlertResponseModel.setItem(itemModel);
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertResponseModel.setPriceRange(rangeModel);
        actualAlertResponseModel.setType(AlertTypeEnum.PRICE_DROP);
        assertEquals("42", actualAlertResponseModel.getAlertIdentifier());
        assertSame(brandModel, actualAlertResponseModel.getBrand());
        assertSame(fromResult, actualAlertResponseModel.getExpiresOn());
        assertEquals("42", actualAlertResponseModel.getId());
        assertSame(itemModel, actualAlertResponseModel.getItem());
        assertSame(rangeModel, actualAlertResponseModel.getPriceRange());
        assertEquals(AlertTypeEnum.PRICE_DROP, actualAlertResponseModel.getType());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        ItemModel item = new ItemModel();
        BrandModel brand = new BrandModel();
        RangeModel<Integer> priceRange = new RangeModel<>();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        AlertResponseModel actualAlertResponseModel = new AlertResponseModel("42", "42", item, brand,
            AlertTypeEnum.PRICE_DROP, priceRange, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        actualAlertResponseModel.setAlertIdentifier("42");
        BrandModel brandModel = new BrandModel();
        actualAlertResponseModel.setBrand(brandModel);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertResponseModel.setExpiresOn(fromResult);
        actualAlertResponseModel.setId("42");
        ItemModel itemModel = new ItemModel();
        actualAlertResponseModel.setItem(itemModel);
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertResponseModel.setPriceRange(rangeModel);
        actualAlertResponseModel.setType(AlertTypeEnum.PRICE_DROP);
        assertEquals("42", actualAlertResponseModel.getAlertIdentifier());
        assertSame(brandModel, actualAlertResponseModel.getBrand());
        assertSame(fromResult, actualAlertResponseModel.getExpiresOn());
        assertEquals("42", actualAlertResponseModel.getId());
        assertSame(itemModel, actualAlertResponseModel.getItem());
        assertSame(rangeModel, actualAlertResponseModel.getPriceRange());
        assertEquals(AlertTypeEnum.PRICE_DROP, actualAlertResponseModel.getType());
    }
}

