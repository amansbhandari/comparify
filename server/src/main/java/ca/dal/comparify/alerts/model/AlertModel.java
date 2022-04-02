package ca.dal.comparify.alerts.model;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.model.RangeModel;
import ca.dal.comparify.utils.UUIDUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

/**
 * @author Harsh Shah
 */
public class AlertModel extends AlertRequestModel {

    @BsonId
    @JsonProperty("_id")
    private String id;

    @BsonProperty("alert_identifier")
    @JsonProperty("alert_identifier")
    private String alertIdentifier;

    @BsonProperty("price_range")
    @JsonProperty("price_range")
    private RangeModel<Integer> priceRange;

    @BsonProperty("expires_on")
    @JsonProperty("expires_on")
    private Date expiresOn;

    private boolean status;

    private AuditModel audit;

    public AlertModel() {
        super();
    }

    public AlertModel(String alertIdentifier,
                      RangeModel<Integer> priceRange,
                      Date expiresOn,
                      boolean status,
                      AuditModel audit) {
        super();
        this.id = UUIDUtils.generate();
        this.alertIdentifier = alertIdentifier;
        this.priceRange = priceRange;
        this.expiresOn = expiresOn;
        this.status = status;
        this.audit = audit;
    }

    public AlertModel(AlertRequestModel request,
                      AuditModel audit) {

        super(request.getItem(), request.getBrand(), request.getType());

        this.id = UUIDUtils.generate();
        this.alertIdentifier = request.getAlertIdentifier();
        this.priceRange = request.getPriceRange();
        this.expiresOn = request.getExpiresOn();
        this.status = true;
        this.audit = audit;
    }

    public static AlertModel transform(AlertRequestModel model, String createBy) {
        return new AlertModel(model, AuditModel.create(createBy));

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getAlertIdentifier() {
        return alertIdentifier;
    }

    @Override
    public void setAlertIdentifier(String alertIdentifier) {
        this.alertIdentifier = alertIdentifier;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }

    @Override
    public RangeModel<Integer> getPriceRange() {
        return priceRange;
    }

    @Override
    public void setPriceRange(RangeModel<Integer> priceRange) {
        this.priceRange = priceRange;
    }

    @Override
    public Date getExpiresOn() {
        return expiresOn;
    }

    @Override
    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }
}
