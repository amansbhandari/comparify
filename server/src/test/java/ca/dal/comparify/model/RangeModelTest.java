package ca.dal.comparify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RangeModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        RangeModel<Object> actualRangeModel = new RangeModel<>();
        actualRangeModel.setMax("Max");
        actualRangeModel.setMin("Min");
        assertEquals("Max", actualRangeModel.getMax());
        assertEquals("Min", actualRangeModel.getMin());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        RangeModel<Object> actualRangeModel = new RangeModel<>("Min", "Max");
        actualRangeModel.setMax("Max");
        actualRangeModel.setMin("Min");
        assertEquals("Max", actualRangeModel.getMax());
        assertEquals("Min", actualRangeModel.getMin());
    }
}

