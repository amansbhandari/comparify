package ca.dal.comparify.brand.model;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Harsh Shah
 */
public class BrandModel {

    @BsonId
    private String id;

    private String name;

    private String description;

    private AuditModel audit;

    public BrandModel(@BsonId String id,
                      @BsonProperty("name") String name,
                      @BsonProperty("description")  String description,
                      @BsonProperty("audit")  AuditModel audit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.audit = audit;
    }

    public BrandModel(String name, String description, AuditModel audit) {
        this.id = UUIDUtils.generate();
        this.name = name;
        this.description = description;
        this.audit = audit;
    }

    public static BrandModel transform(BrandRequestModel model, String createdBy){
        return new BrandModel(model.getName(), model.getDescription(), AuditModel.create(createdBy));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }
}
