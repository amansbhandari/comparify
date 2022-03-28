package ca.dal.comparify.brand.model;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Harsh Shah
 */
public class BrandModel extends BrandRequestModel {

    @BsonId
    private String id;

    private AuditModel audit;

    public BrandModel() {
        super();
    }

    public BrandModel(@BsonId String id,
                      @BsonProperty("name") String name,
                      @BsonProperty("description")  String description,
                      @BsonProperty("audit")  AuditModel audit) {
        super(name, description);
        this.id = id;
        this.audit = audit;
    }

    public BrandModel(String name, String description, AuditModel audit) {
        super(name, description);
        this.id = UUIDUtils.generate();
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

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }
}
