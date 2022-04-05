package ca.dal.comparify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EntityReferenceTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        EntityReference actualEntityReference = new EntityReference();
        actualEntityReference.setEntityId("42");
        actualEntityReference.setEntityType("Entity Type");
        assertEquals("42", actualEntityReference.getEntityId());
        assertEquals("Entity Type", actualEntityReference.getEntityType());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        EntityReference actualEntityReference = new EntityReference("42", "Entity Type");
        actualEntityReference.setEntityId("42");
        actualEntityReference.setEntityType("Entity Type");
        assertEquals("42", actualEntityReference.getEntityId());
        assertEquals("Entity Type", actualEntityReference.getEntityType());
    }
}

