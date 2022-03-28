package ca.dal.comparify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Harsh Shah
 */
public class EntityReference {

    @BsonProperty("entity_id")
    @JsonProperty("entity_id")
    private String entityId;

    @BsonProperty("entity_type")
    @JsonProperty("entity_type")
    private String entityType;

    public EntityReference(){}

    public EntityReference(@BsonProperty("entity_id") String entityId,
                           @BsonProperty("entity_type") String entityType) {
        this.entityId = entityId;
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
