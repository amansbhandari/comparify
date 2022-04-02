package ca.dal.comparify.alerts.model;

/**
 * @author Harsh Shah
 */
public enum AlertTypeEnum {

    PRICE_DROP("PRICE_DROP"),
    PRICE_RANGE("PRICE_RANGE"),
    PRODUCT_INFORMATION_AVAILABLE("PRODUCT_INFORMATION_AVAILABLE");

    private String value;

    AlertTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getValueLowerCase() {
        return value.toLowerCase();
    }
}
