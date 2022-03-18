package ca.dal.comparify.mongo;

/**
 * @author Harsh Shah
 */
public class Tuple {

    private final String key;

    private final Object value;

    public Tuple(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static Tuple tuple(String key, Object value){
        return new Tuple(key, value);
    }

    public String getKey() {
        return key;
    }


    public Object getValue() {
        return value;
    }


}
