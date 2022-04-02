package ca.dal.comparify.notification.model;

import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Harsh Shah
 */
public class NotificationModel implements Serializable {

    @BsonId
    private String id;

    @BsonProperty("for")
    private String forUser;

    private String title;

    private String message;

    private NotificationTypeEnum type;

    private Map<String, Object> model;

    private AuditModel audit;

    public NotificationModel() {
    }

    public NotificationModel(String id, NotificationTypeEnum type, HashModel model, AuditModel audit) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.audit = audit;
    }

    public NotificationModel(String forUser, String title, String message, NotificationTypeEnum type,
                             HashModel model) {
        this.id = UUIDUtils.generate();
        this.forUser = forUser;
        this.title = title;
        this.message = message;
        this.type = type;
        this.model = model;
        this.audit = AuditModel.create(ApplicationConstant.SYSTEM);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }

    public NotificationTypeEnum getType() {
        return type;
    }

    public void setType(NotificationTypeEnum type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }
}
