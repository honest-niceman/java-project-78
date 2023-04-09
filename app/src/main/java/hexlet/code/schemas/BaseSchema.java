package hexlet.code.schemas;

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
    public T required() {
        this.isRequired = true;
        return (T) this;
    }
}
