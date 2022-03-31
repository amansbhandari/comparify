package ca.dal.comparify.compareitems.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.UUID;

/**
 * @author Chanpreet Singh
 */
public class CompareItemsModel {

    @BsonId
    private ObjectId id;

    @JsonProperty
    private String imageText, userId, productId, storeId, brandId;
    @JsonProperty
    private Double price, priceAfterDiscount, unit;
    @JsonProperty
    private Date dateOfPurchase;

    public CompareItemsModel() {
    }

    public CompareItemsModel(@BsonId ObjectId id,
                             @BsonProperty("image_text") String imageText,
                             @BsonProperty("user_id") String userId,
                             @BsonProperty("product_id") String productId,
                             @BsonProperty("price") Double price,
                             @BsonProperty("price_after_discount") Double priceAfterDiscount,
                             @BsonProperty("store_id") String storeId,
                             @BsonProperty("date_of_purchase") Date dateOfPurchase,
                             @BsonProperty("unit") Double unit,
                             @BsonProperty("brand_id") String brandId) {
        this.id = id;
        this.imageText = imageText;
        this.userId = userId;
        this.productId = productId;
        this.storeId = storeId;
        this.unit = unit;
        this.brandId = brandId;
        this.price = price;
        this.priceAfterDiscount = priceAfterDiscount;
        this.dateOfPurchase = dateOfPurchase;
    }

    public CompareItemsModel(String imageText, String userId, String productId, Double price, Double priceAfterDiscount, String storeId, Date dateOfPurchase, Double unit, String brandId){
        this.setBrandId(UUID.randomUUID().toString());
        this.setImageText(imageText);
        this.setUserId(userId);
        this.setProductId(productId);
        this.setStoreId(storeId);
        this.setUnit(unit);
        this.setBrandId(brandId);
        this.setPrice(price);
        this.setPriceAfterDiscount(priceAfterDiscount);
        this.setDateOfPurchase(dateOfPurchase);
    }

    public static CompareItemsModel create(CompareItemsModel model){
        return new CompareItemsModel(model.getImageText(), model.getUserId(), model.getProductId(), model.getPrice(), model.getPriceAfterDiscount(), model.getStoreId(), model.getDateOfPurchase(), model.getUnit(), model.getBrandId());
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(Double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }

    public Date getDateOfPurchase() { return dateOfPurchase; }

    public void setDateOfPurchase(Date dateOfPurchase) { this.dateOfPurchase = dateOfPurchase;}
}
