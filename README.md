### Hexlet tests and linter status:
[![Actions Status](https://github.com/honest-niceman/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/honest-niceman/java-project-78/actions)

[![Maintainability](https://api.codeclimate.com/v1/badges/8d08dbb3857c9359ab9a/maintainability)](https://codeclimate.com/github/honest-niceman/java-project-78/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/8d08dbb3857c9359ab9a/test_coverage)](https://codeclimate.com/github/honest-niceman/java-project-78/test_coverage)

A data validator is a library with which you can check the correctness of any data.

## Example

```java
public class App {
    public static void main(String[] args) {
        Validator v = new Validator();

        // строки
        StringSchema stringSchema = v.string().required();

        stringSchema.isValid("what does the fox say"); // true
        stringSchema.isValid(""); // false

        // числа
        NumberSchema numberSchema = v.number().required().positive();

        numberSchema.isValid(-10); // false
        numberSchema.isValid(10); // true

        // объект Map с поддержкой проверки структуры
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        MapSchema map = v.map();
        MapSchema mapSchema = map.sizeof(2);
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        mapSchema.isValid(human1); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "");
        human2.put("age", null);
        mapSchema.isValid(human1); // false
    }
}
```
