package ca.dal.comparify.user.enumeration;

public enum UserErrorCode {

    E2000(2000), E2001(2001), E2002(2002), E2003(2003), E2004(2004), E2005(2005);

    private Integer code;

    UserErrorCode(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
