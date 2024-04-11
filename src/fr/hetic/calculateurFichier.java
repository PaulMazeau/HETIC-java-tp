import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class CalculateurFichier {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CalculateurFichier <chemin_dossier>");
            return;
        }

        File dossier = new File(args[0]);
        File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".op"));

        if (fichiers == null) {
            System.out.println("Erreur lors de la lecture du dossier.");
            return;
        }

        Arrays.stream(fichiers).forEach(CalculateurFichier::traiterFichier);
    }

    private static void traiterFichier(File fichier) {
        File fichierSortie = new File(fichier.getParent(), fichier.getName().replace(".op", ".res"));

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fichierSortie))) {

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                try {
                    String resultat = calculerLigne(ligne);
                    writer.write(resultat);
                } catch (OperationInvalideException | FormatLigneException e) {
                    writer.write("ERROR");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture/écriture : " + e.getMessage());
        }
    }

    private static String calculerLigne(String ligne) throws OperationInvalideException, FormatLigneException {
        String[] parties = ligne.split(";");
        if (parties.length != 3) {
            throw new FormatLigneException("Format de ligne invalide");
        }

        double nombre1, nombre2;
        try {
            nombre1 = Double.parseDouble(parties[0]);
            nombre2 = Double.parseDouble(parties[1]);
        } catch (NumberFormatException e) {
            throw new FormatLigneException("Erreur de format numérique");
        }

        String operateur = parties[2];
        switch (operateur) {
            case "+":
                return String.valueOf(nombre1 + nombre2);
            case "-":
                return String.valueOf(nombre1 - nombre2);
            case "*":
                return String.valueOf(nombre1 * nombre2);
            default:
                throw new OperationInvalideException("Opérateur non supporté");
        }
    }

    static class OperationInvalideException extends Exception {
        public OperationInvalideException(String message) {
            super(message);
        }
    }

    static class FormatLigneException extends Exception {
        public FormatLigneException(String message) {
            super(message);
        }
    }
}
