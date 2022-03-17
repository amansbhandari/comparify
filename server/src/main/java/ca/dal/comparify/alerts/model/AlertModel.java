package ca.dal.comparify.alerts.model;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.model.EntityReference;
import ca.dal.comparify.model.RangeModel;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

/**
 * @author Harsh Shah
 */
public class AlertModel {

    @BsonId
    private String id;

    @BsonProperty("alert_identifier")
    private String alertIdentifier;

    private EntityReference item;

    private EntityReference brand;

    private AlertTypeEnum type;

    @BsonProperty("price_range")
    private RangeModel<Integer> priceRange;

    @BsonProperty("expires_on")
    private Date expiresOn;

    private AuditModel audit;


    public AlertModel(@BsonId String id,
                      @BsonProperty("alert_identifier") String alertIdentifier,
                      @BsonProperty("item") EntityReference item,
                      @BsonProperty("brand") EntityReference brand,
                      @BsonProperty("type") AlertTypeEnum type,
                      @BsonProperty("price_range") RangeModel<Integer> priceRange,
                      @BsonProperty("expires_on") Date expiresOn,
                      @BsonProperty("audit") AuditModel audit) {
        this.id = id;
        this.alertIdentifier = alertIdentifier;
        this.item = item;
        this.brand = brand;
        this.type = type;
        this.priceRange = priceRange;
        this.expiresOn = expiresOn;
        this.audit = audit;
    }

    public AlertModel(String alertIdentifier,
                      EntityReference item,
                      EntityReference brand,
                      AlertTypeEnum type,
                      RangeModel<Integer> priceRange,
                      Date expiresOn,
                      AuditModel audit) {
        this.id = UUIDUtils.generate();
        this.alertIdentifier = alertIdentifier;
        this.item = item;
        this.brand = brand;
        this.type = type;
        this.priceRange = priceRange;
        this.expiresOn = expiresOn;
        this.audit = audit;
    }

    public static AlertModel transform(AlertRequestModel model, String createBy) {
        return new AlertModel(model.getAlertIdentifier(), model.getItem(), model.getBrand(),
                model.getType(), model.getPriceRange(), model.getExpiresOn(),
                AuditModel.create(createBy));

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlertIdentifier() {
        return alertIdentifier;
    }

    public void setAlertIdentifier(String alertIdentifier) {
        this.alertIdentifier = alertIdentifier;
    }

    public EntityReference getItem() {
        return item;
    }

    public void setItem(EntityReference item) {
        this.item = item;
    }

    public EntityReference getBrand() {
        return brand;
    }

    public void setBrand(EntityReference brand) {
        this.brand = brand;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }

    public AlertTypeEnum getType() {
        return type;
    }

    public void setType(AlertTypeEnum type) {
        this.type = type;
    }

    public RangeModel<Integer> getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(RangeModel<Integer> priceRange) {
        this.priceRange = priceRange;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }
}
