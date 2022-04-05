package ca.dal.comparify.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.model.RangeModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class AlertModelTest {
    @Test
    void testConstructor() {
        AlertModel actualAlertModel = new AlertModel();
        actualAlertModel.setAlertIdentifier("42");
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualAlertModel.setAudit(createResult);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertModel.setExpiresOn(fromResult);
        actualAlertModel.setId("42");
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertModel.setPriceRange(rangeModel);
        actualAlertModel.setStatus(true);
        assertEquals("42", actualAlertModel.getAlertIdentifier());
        assertSame(createResult, actualAlertModel.getAudit());
        assertSame(fromResult, actualAlertModel.getExpiresOn());
        assertEquals("42", actualAlertModel.getId());
        assertSame(rangeModel, actualAlertModel.getPriceRange());
        assertTrue(actualAlertModel.isStatus());
    }

    @Test
    void testConstructor2() {
        AlertRequestModel request = new AlertRequestModel();
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        AlertModel actualAlertModel = new AlertModel(request, createResult);

        assertNull(actualAlertModel.getAlertIdentifier());
        assertTrue(actualAlertModel.isStatus());
        assertNull(actualAlertModel.getType());
        assertNull(actualAlertModel.getPriceRange());
        assertNull(actualAlertModel.getItem());
        assertNull(actualAlertModel.getExpiresOn());
        assertNull(actualAlertModel.getBrand());
        assertSame(createResult, actualAlertModel.getAudit());
    }

    @Test
    void testConstructor3() {
        AlertModel request = new AlertModel();
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        AlertModel actualAlertModel = new AlertModel(request, createResult);

        assertNull(actualAlertModel.getAlertIdentifier());
        assertTrue(actualAlertModel.isStatus());
        assertNull(actualAlertModel.getType());
        assertNull(actualAlertModel.getPriceRange());
        assertNull(actualAlertModel.getItem());
        assertNull(actualAlertModel.getExpiresOn());
        assertNull(actualAlertModel.getBrand());
        assertSame(createResult, actualAlertModel.getAudit());
    }

    @Test
    void testConstructor4() {
        RangeModel<Integer> rangeModel = new RangeModel<>();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        AlertModel actualAlertModel = new AlertModel("42", rangeModel, fromResult, true, createResult);

        assertEquals("42", actualAlertModel.getAlertIdentifier());
        assertTrue(actualAlertModel.isStatus());
        assertSame(rangeModel, actualAlertModel.getPriceRange());
        assertSame(createResult, actualAlertModel.getAudit());
        assertSame(fromResult, actualAlertModel.getExpiresOn());
    }

    @Test
    void testTransform() {
        AlertModel actualTransformResult = AlertModel.transform(new AlertRequestModel(), "Create By");
        assertNull(actualTransformResult.getAlertIdentifier());
        assertTrue(actualTransformResult.isStatus());
        assertNull(actualTransformResult.getType());
        assertNull(actualTransformResult.getPriceRange());
        assertNull(actualTransformResult.getItem());
        assertNull(actualTransformResult.getExpiresOn());
        assertNull(actualTransformResult.getBrand());
        assertEquals("Create By", actualTransformResult.getAudit().getCreatedBy());
    }

    @Test
    void testTransform2() {
        AlertModel actualTransformResult = AlertModel.transform(new AlertModel(), "Create By");
        assertNull(actualTransformResult.getAlertIdentifier());
        assertTrue(actualTransformResult.isStatus());
        assertNull(actualTransformResult.getType());
        assertNull(actualTransformResult.getPriceRange());
        assertNull(actualTransformResult.getItem());
        assertNull(actualTransformResult.getExpiresOn());
        assertNull(actualTransformResult.getBrand());
        assertEquals("Create By", actualTransformResult.getAudit().getCreatedBy());
    }
}

