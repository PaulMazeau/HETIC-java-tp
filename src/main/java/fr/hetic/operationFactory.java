import java.util.Map;
import java.util.function.BinaryOperator;

public class OperationFactory {
    private static final Map<String, BinaryOperator<Double>> OPERATIONS = Map.of(
        "+", (a, b) -> a + b,
        "-", (a, b) -> a - b,
        "*", (a, b) -> a * b
    );

    public static BinaryOperator<Double> getOperation(String operator) {
        return OPERATIONS.getOrDefault(operator, (a, b) -> {
            throw new IllegalArgumentException("Opérateur non supporté: " + operator);
        });
    }
}
