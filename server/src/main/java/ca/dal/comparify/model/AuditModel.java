package ca.dal.comparify.model;

import ca.dal.comparify.utils.DateUtils;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

/**
 * @author Harsh Shah
 */
public class AuditModel {

    public static final String CREATED_BY = "created_by";
    public static final String UPDATED_BY = "updated_by";
    public static final String CREATED_ON = "created_on";
    public static final String UPDATED_ON = "updated_on";

    @BsonProperty(CREATED_BY)
    private String createdBy;

    @BsonProperty(UPDATED_BY)
    private String updatedBy;

    @BsonProperty(CREATED_ON)
    private Date createdOn;

    @BsonProperty(UPDATED_ON)
    private Date updatedOn;

    public AuditModel() {
    }

    public AuditModel(@BsonProperty(CREATED_BY) String createdBy,
                      @BsonProperty(UPDATED_BY) String updatedBy,
                      @BsonProperty(CREATED_ON) Date createdOn,
                      @BsonProperty(UPDATED_ON) Date updatedOn) {

        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public static AuditModel create(String createdBy) {

        AuditModel model = new AuditModel();

        model.setCreatedBy(createdBy);
        model.setCreatedOn(DateUtils.dateNow());

        return model;
    }

    public static AuditModel create(String createdBy, Date createdOn) {

        AuditModel model = new AuditModel();

        model.setCreatedBy(createdBy);
        model.setCreatedOn(createdOn);

        return model;
    }

    public static AuditModel update(String updatedBy) {

        AuditModel model = new AuditModel();

        model.setUpdatedBy(updatedBy);
        model.setUpdatedOn(DateUtils.dateNow());

        return model;
    }

    public static AuditModel update(String updatedBy, Date updatedOn) {

        AuditModel model = new AuditModel();

        model.setUpdatedBy(updatedBy);
        model.setUpdatedOn(updatedOn);

        return model;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
