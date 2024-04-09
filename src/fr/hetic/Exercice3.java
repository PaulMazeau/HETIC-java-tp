interface Operation {
    double execute(double num1, double num2);
}

class Addition implements Operation {
    public double execute(double num1, double num2) {
        return num1 + num2;
    }
}

class Subtraction implements Operation {
    public double execute(double num1, double num2) {
        return num1 - num2;
    }
}

class Multiplication implements Operation {
    public double execute(double num1, double num2) {
        return num1 * num2;
    }
}

class OperationFactory {
    public static Operation getOperation(String operator) throws IllegalArgumentException {
        switch (operator) {
            case "+": return new Addition();
            case "-": return new Subtraction();
            case "*": return new Multiplication();
            default: throw new IllegalArgumentException("Opérateur non supporté");
        }
    }
}
