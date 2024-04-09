public class Calculateur {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculateur <nombre1> <nombre2> <opérateur>");
            return;
        }

        try {
            double num1 = Double.parseDouble(args[0]);
            double num2 = Double.parseDouble(args[1]);
            String operator = args[2];

            double result = calculate(num1, num2, operator);
            System.out.println("Résultat : " + result);
        } catch (Exception e) {
            System.out.println("Erreur lors du calcul: " + e.getMessage());
        }
    }

    private static double calculate(double num1, double num2, String operator) throws Exception {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            default:
                throw new IllegalArgumentException("Opérateur non supporté: " + operator);
        }
    }
}
