package ca.dal.comparify.item.model;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Chanpreet Singh
 */
public class ItemModel extends ItemRequestModel {

    private String id;

    @BsonProperty("default_image")
    private String defaultImage;

    private AuditModel audit;

    public ItemModel() {
    }

    public ItemModel(@BsonId String id,
                     @BsonProperty("name") String name,
                     @BsonProperty("description") String description,
                     @BsonProperty("default_image") String defaultImage,
                     @BsonProperty("itemCategoryId") String itemCategoryId,
                     @BsonProperty("audit") AuditModel audit) {
        super(name, description, defaultImage, itemCategoryId);
        this.id = id;
        this.audit = audit;
    }

    public ItemModel(String name, String description, String defaultImage, String itemCategoryId, AuditModel audit) {
        super(name, description, defaultImage, itemCategoryId);
        this.id = UUIDUtils.generate();
        this.defaultImage = defaultImage;
        this.audit = audit;
    }

    public static ItemModel create(ItemRequestModel model, String createdBy){
        return new ItemModel(model.getName(), model.getDescription(), model.getDefaultImage(), model.getItemCategoryId(), AuditModel.create(createdBy));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getDefaultImage() {
        return defaultImage;
    }

    @Override
    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }
}
