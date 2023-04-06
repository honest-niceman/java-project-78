package hexlet.code;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema {
    private boolean isRequired;
    private int minLength;
    private final List<String> containsRules;

    public StringSchema() {
        this.isRequired = false;
        this.minLength = -1;
        this.containsRules = new ArrayList<>();
    }

    public boolean isValid(Object o) {
        if (!isRequired && o == null) {
            return true;
        }
        if (!(o instanceof String)) {
            return false;
        }
        boolean required = checkRequired((String) o);
        boolean minLength = checkMinLength((String) o);
        boolean checkContains = checkContainsRules((String) o);
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

    public void required() {
        this.isRequired = true;
    }

    public void minLength(int minLength) {
        this.minLength = minLength;
    }
}
