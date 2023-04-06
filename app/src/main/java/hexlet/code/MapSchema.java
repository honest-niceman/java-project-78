package hexlet.code;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {
    private int sizeof;

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
        return checkSizeOf(map);
    }

    private boolean checkSizeOf(Map map) {
        if (sizeof == -1) {
            return true;
        }
        return map.size() == sizeof;
    }

    public MapSchema sizeof(int sizeof) {
        this.sizeof = sizeof;
        return this;
    }
}
