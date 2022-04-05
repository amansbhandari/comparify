package ca.dal.comparify.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TupleTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        assertEquals("Key", (new Tuple("Key", "Value")).getKey());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testTuple() {
        Tuple actualTupleResult = Tuple.tuple("Key", "Value");
        assertEquals("Key", actualTupleResult.getKey());
        assertEquals("Value", actualTupleResult.getValue());
    }
}

