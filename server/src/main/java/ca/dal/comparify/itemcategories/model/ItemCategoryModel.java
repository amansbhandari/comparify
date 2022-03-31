package ca.dal.comparify.itemcategories.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * @author Meghna Rupchandani
 */
public class ItemCategoryModel {

    @BsonProperty("_id")
    @BsonId
    private ObjectId id;

    private String categoryName;

    public ItemCategoryModel() {
    }

    public ItemCategoryModel(@BsonProperty("_id") ObjectId id,
                             @BsonProperty("categoryName") String storeName
                        ){
        this.id = id;
        this.categoryName = categoryName;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
