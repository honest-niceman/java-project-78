package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addCheck(CheckName.REQUIRED, value -> value instanceof String && !((String) value).isEmpty());
    }

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(CheckName.CONTAINS, value -> ((String) value).contains(substring));
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(CheckName.MIN_LENGTH, value -> ((String) value).length() >= length);
        return this;
    }

}
