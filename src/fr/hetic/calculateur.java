public class Calculateur {

    public static void main(String[] args) {
        if (args.length != 3) {
            afficherUsage();
            return;
        }

        try {
            double nombre1 = Double.parseDouble(args[0]);
            double nombre2 = Double.parseDouble(args[1]);
            String operateur = args[2];

            double resultat = effectuerOperation(nombre1, nombre2, operateur);
            System.out.println("Résultat: " + resultat);
        } catch (NumberFormatException e) {
            System.out.println("Les deux premiers arguments doivent être des nombres.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void afficherUsage() {
        System.out.println("Usage: java Calculateur <nombre1> <nombre2> <operateur>");
    }

    private static double effectuerOperation(double nombre1, double nombre2, String operateur) {
        switch (operateur) {
            case "+":
                return nombre1 + nombre2;
            case "-":
                return nombre1 - nombre2;
            case "*":
                return nombre1 * nombre2;
            default:
                throw new IllegalArgumentException("Opérateur non reconnu. Les opérateurs valides sont +, -, *.");
        }
    }
}
