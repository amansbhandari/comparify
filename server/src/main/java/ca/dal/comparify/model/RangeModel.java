package ca.dal.comparify.model;

/**
 * @author Harsh Shah
 */
public class RangeModel<T> {

    private T min;

    private T max;

    public RangeModel() {}

    public RangeModel(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }
}
