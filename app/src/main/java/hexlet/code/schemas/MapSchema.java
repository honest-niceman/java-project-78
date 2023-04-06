package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {
    private int sizeof;
    private Map<String, BaseSchema> schemas;

    public MapSchema() {
        super();
        this.sizeof = -1;
    }

    @Override
    public boolean isValid(Object o) {
        if (!isRequired && o == null) {
            return true;
        }
        if (!(o instanceof Map map)) {
            return false;
        }
        if (schemas != null && !validateShape(map)) {
            return false;
        }
        return checkSizeOf(map);
    }

    private boolean checkSizeOf(Map map) {
        if (sizeof == -1) {
            return true;
        }
        return map.size() == sizeof;
    }

    private boolean validateShape(Map map) {
        for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema schema = entry.getValue();
            Object value = map.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }

    public MapSchema sizeof(int sizeof) {
        this.sizeof = sizeof;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.schemas = schemas;
        return this;
    }
}
