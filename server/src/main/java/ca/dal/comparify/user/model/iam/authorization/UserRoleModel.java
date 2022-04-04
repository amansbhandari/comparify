package ca.dal.comparify.user.model.iam.authorization;

import ca.dal.comparify.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Harsh Shah
 */
public class UserRoleModel {


    private String id;

    @JsonProperty("role_id")
    private UserAuthorizationRoleEnum roleId;

    private AuditModel audit;

    public UserRoleModel(UserAuthorizationRoleEnum roleId) {
        this.roleId = roleId;
    }

    public UserRoleModel(String id, UserAuthorizationRoleEnum roleId, AuditModel audit) {
        this.id = id;
        this.roleId = roleId;
        this.audit = audit;
    }

    public UserRoleModel(String id, UserAuthorizationModel authorizationModel) {
        this.id = id;
        this.roleId = authorizationModel.getRoleId();
        this.audit = authorizationModel.getAudit();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
