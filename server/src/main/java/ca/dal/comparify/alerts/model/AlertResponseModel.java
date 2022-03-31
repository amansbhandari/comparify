package ca.dal.comparify.alerts.model;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.model.RangeModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

/**
 * @author Harsh Shah
 */
public class AlertResponseModel {

    @BsonProperty("_id")
    private String id;

    @JsonProperty("alert_identifier")
    @BsonProperty("alert_identifier")
    private String alertIdentifier;

    private ItemModel item;

    private BrandModel brand;

    private AlertTypeEnum type;

    @JsonProperty("price_range")
    @BsonProperty("price_range")
    private RangeModel<Integer> priceRange;

    @JsonProperty("expires_on")
    @BsonProperty("expires_on")
    private Date expiresOn;

    public AlertResponseModel() {
    }

    public AlertResponseModel(String id, String alertIdentifier, ItemModel item,
                              BrandModel brand, AlertTypeEnum type,
                              RangeModel<Integer> priceRange, Date expiresOn) {
        this.id = id;
        this.alertIdentifier = alertIdentifier;
        this.item = item;
        this.brand = brand;
        this.type = type;
        this.priceRange = priceRange;
        this.expiresOn = expiresOn;
    }

    public AlertResponseModel(ItemModel item, BrandModel brand, AlertTypeEnum type) {
        this.item = item;
        this.brand = brand;
        this.type = type;
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

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public BrandModel getBrand() {
        return brand;
    }

    public void setBrand(BrandModel brand) {
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
