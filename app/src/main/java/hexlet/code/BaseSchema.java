package hexlet.code;

public abstract class BaseSchema<T> {
    protected boolean isRequired;

    public BaseSchema() {
        this.isRequired = false;
    }

    public abstract boolean isValid(Object o);

    /**
     * Specifies required value.
     * @return the BaseSchema itself
     */
    public BaseSchema required() {
        this.isRequired = true;
        return this;
    }
}
