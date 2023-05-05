package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addCheck(CheckName.REQUIRED, value -> value instanceof Integer);
    }

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck(CheckName.POSITIVE, value -> ((int) value) > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(CheckName.RANGE, value -> ((int) value) >= min && ((int) value) <= max);
        return this;
    }

}
