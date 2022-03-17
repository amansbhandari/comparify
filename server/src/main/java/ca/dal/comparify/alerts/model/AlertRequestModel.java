package ca.dal.comparify.alerts.model;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.model.EntityReference;
import ca.dal.comparify.model.RangeModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

/**
 * @author Harsh Shah
 */
public class AlertRequestModel {

    @JsonProperty("alert_identifier")
    private String alertIdentifier;

    private EntityReference item;

    private EntityReference brand;

    private AlertTypeEnum type;

    private RangeModel<Integer> priceRange;

    private Date expiresOn;

    public AlertRequestModel(String alertIdentifier, EntityReference item, EntityReference brand,
                             AlertTypeEnum type, RangeModel<Integer> priceRange, Date expiresOn) {
        this.alertIdentifier = alertIdentifier;
        this.item = item;
        this.brand = brand;
        this.type = type;
        this.priceRange = priceRange;
        this.expiresOn = expiresOn;
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
