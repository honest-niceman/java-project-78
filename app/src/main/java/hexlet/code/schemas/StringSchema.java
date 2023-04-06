package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema<String> {
    private int minLength;
    private final List<String> containsRules;

    public StringSchema() {
        super();
        this.minLength = -1;
        this.containsRules = new ArrayList<>();
    }

    public boolean isValid(Object o) {
        if (!isRequired && o == null) {
            return true;
        }
        if (!(o instanceof String s)) {
            return false;
        }
        boolean required = checkRequired(s);
        boolean minLength = checkMinLength(s);
        boolean checkContains = checkContainsRules(s);
        return required && minLength && checkContains;
    }

    private boolean checkContainsRules(String s) {
        if (containsRules.isEmpty()) {
            return true;
        }
        boolean result = true;
        for (String containsRule : containsRules) {
            if (!s.contains(containsRule)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean checkMinLength(String s) {
        if (minLength != -1) {
            return s.length() >= minLength;
        } else {
            return true;
        }
    }

    private boolean checkRequired(String s) {
        if (isRequired) {
            return !s.isEmpty();
        } else {
            return true;
        }
    }

    public StringSchema contains(String s) {
        this.containsRules.add(s);
        return this;
    }

    public void minLength(int minLength) {
        this.minLength = minLength;
    }
}
