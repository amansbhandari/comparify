package ca.dal.comparify.user.model.iam.authorization;

/**
 * @author Harsh Shah
 */
public enum UserAuthorizationRoleEnum {

    USER("USER"), ADMIN("ADMIN");

    private String value;

    UserAuthorizationRoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
