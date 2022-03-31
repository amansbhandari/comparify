package ca.dal.comparify.notification.model;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.model.HashModel;

/**
 * @author Harsh Shah
 */
public class NotificationModel {

    private String id;

    private NotificationTypeEnum type;

    private HashModel model;

    private AuditModel audit;

    public NotificationModel() {
    }

    public NotificationModel(String id, NotificationTypeEnum type, HashModel model, AuditModel audit) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.audit = audit;
    }

    public NotificationModel(String id, NotificationTypeEnum type, HashModel model, String createdBy) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.audit = AuditModel.create(createdBy);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationTypeEnum getType() {
        return type;
    }

    public void setType(NotificationTypeEnum type) {
        this.type = type;
    }

    public HashModel getModel() {
        return model;
    }

    public void setModel(HashModel model) {
        this.model = model;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }
}
