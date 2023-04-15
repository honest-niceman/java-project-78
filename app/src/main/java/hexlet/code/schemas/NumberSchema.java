package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private boolean isPositive;
    private final Integer[] range;
    private boolean isRangeEnabled;

    public NumberSchema() {
        super();
        this.isPositive = false;
        this.range = new Integer[2];
        this.isRangeEnabled = false;
    }

    public boolean isValid(Object o) {
        if (!isRequired && o == null) {
            return true;
        }
        if (!(o instanceof Integer)) {
            return false;
        }
        int i = (Integer) o;
        boolean required = checkRequired(i);
        boolean isPositive = checkPositive(i);
        boolean checkRange = checkRange(i);
        return required && isPositive && checkRange;
    }

    private boolean checkRange(Integer o) {
        if (!isRangeEnabled) {
            return true;
        }
        return o >= range[0] && o <= range[1];
    }

    private boolean checkPositive(Integer o) {
        return o > 0 || !isPositive;
    }

    private boolean checkRequired(Integer s) {
        return !isRequired || s != null;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public void range(int a, int b) {
        this.isRangeEnabled = true;
        this.range[0] = a;
        this.range[1] = b;
    }

    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }
}
