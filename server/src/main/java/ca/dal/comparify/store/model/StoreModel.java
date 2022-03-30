package ca.dal.comparify.store.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * @author Meghna Rupchandani
 */
public class StoreModel{

    @BsonProperty("_id")
    @BsonId
    private ObjectId id;

    private String storeName, streetName, city, phone;

    public StoreModel() {
    }

    public StoreModel(@BsonProperty("_id") ObjectId id,
                      @BsonProperty("storeName") String storeName,
                      @BsonProperty("streetName")  String streetName,
                      @BsonProperty("city")  String city,
                      @BsonProperty("phone") String phone) {
        this.id = id;
        this.storeName = storeName;
        this.streetName = streetName;
        this.city = city;
        this.phone = phone;

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
