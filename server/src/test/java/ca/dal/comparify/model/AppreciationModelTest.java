package ca.dal.comparify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class AppreciationModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        AppreciationModel actualAppreciationModel = new AppreciationModel();
        actualAppreciationModel.setPoints(10.0d);
        actualAppreciationModel.setType("Type");
        actualAppreciationModel.setUsername("janedoe");
        assertEquals(10.0d, actualAppreciationModel.getPoints().doubleValue());
        assertEquals("Type", actualAppreciationModel.getType());
        assertEquals("janedoe", actualAppreciationModel.getUsername());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        AppreciationModel actualAppreciationModel = new AppreciationModel("janedoe", 10.0d, "Type");
        actualAppreciationModel.setPoints(10.0d);
        actualAppreciationModel.setType("Type");
        actualAppreciationModel.setUsername("janedoe");
        assertEquals(10.0d, actualAppreciationModel.getPoints().doubleValue());
        assertEquals("Type", actualAppreciationModel.getType());
        assertEquals("janedoe", actualAppreciationModel.getUsername());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsEmpty() {
        assertTrue((new AppreciationModel()).isEmpty());
        assertFalse((new AppreciationModel("janedoe", 10.0d, "Type")).isEmpty());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testGetRequiredFields() {
        List<String> actualRequiredFields = (new AppreciationModel()).getRequiredFields();
        assertEquals(2, actualRequiredFields.size());
        assertNull(actualRequiredFields.get(0));
        assertNull(actualRequiredFields.get(1));
    }
}

