package hexlet.code;

public class App {
    public static void main(String[] args) {
        Validator v = new Validator();

        StringSchema schema = v.string();

        System.out.println(schema.isValid("")); // true
        // Пока на вызван метод required(), null считается валидным
        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("hexlet")); // true
        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid(5)); // false
        System.out.println(schema.isValid("")); // false

        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema.contains("whatthe").isValid("what does the fox say")); // false

        System.out.println(schema.isValid("what does the fox say")); // false
        // уже false, так как добавлена ещё одна проверка contains("whatthe")
    }
}