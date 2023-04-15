package hexlet.code.schemas;

public abstract class BaseSchema {
    protected boolean isRequired;

    public BaseSchema() {
        this.isRequired = false;
    }

    public abstract boolean isValid(Object o);
}
