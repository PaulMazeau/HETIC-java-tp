public class AdditionStrategy implements OperationStrategy {
    @Override
    public double execute(double op1, double op2) {
        return op1 + op2;
    }
}

public class SubtractionStrategy implements OperationStrategy {
    @Override
    public double execute(double op1, double op2) {
        return op1 - op2;
    }
}

public class MultiplicationStrategy implements OperationStrategy {
    @Override
    public double execute(double op1, double op2) {
        return op1 * op2;
    }
}
