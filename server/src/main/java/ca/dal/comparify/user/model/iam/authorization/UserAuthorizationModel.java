package ca.dal.comparify.user.model.iam.authorization;

import ca.dal.comparify.model.AuditModel;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Harsh Shah
 */
public class UserAuthorizationModel {

    public static final String CREATE_ACTION = "create";
    public static final String UPDATE_ACTION = "update";

    @BsonProperty("role_id")
    private UserAuthorizationRoleEnum roleId;

    private AuditModel audit;

    public UserAuthorizationModel() {}

    public UserAuthorizationModel(@BsonProperty("role_id") UserAuthorizationRoleEnum roleId,
                                  @BsonProperty("audit") AuditModel audit) {
        this.roleId = roleId;
        this.audit = audit;
    }

    public UserAuthorizationModel(UserAuthorizationRoleEnum roleId, String actionType, String by) {
        this.roleId = roleId;
        this.audit = CREATE_ACTION.equals(actionType) ? AuditModel.create(by) : AuditModel.update(by);
    }

    public UserAuthorizationRoleEnum getRoleId() {
        return roleId;
    }

    public void setRoleId(UserAuthorizationRoleEnum roleId) {
        this.roleId = roleId;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }
}
