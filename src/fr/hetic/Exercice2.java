import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileProcessor {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileProcessor <dossier>");
            return;
        }

        File folder = new File(args[0]);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".op"));

        for (File file : files) {
            processFile(file);
        }
    }

    private static void processFile(File file) {
        File resultFile = new File(file.getParent(), file.getName().replace(".op", ".res"));
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" ");
                    double num1 = Double.parseDouble(parts[0]);
                    double num2 = Double.parseDouble(parts[1]);
                    String operator = parts[2];
                    double result = Calculateur.calculate(num1, num2, operator);
                    writer.write(String.valueOf(result));
                } catch (Exception e) {
                    writer.write("ERROR");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du traitement du fichier: " + e.getMessage());
        }
    }
}
