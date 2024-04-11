package fr.hetic;

import java.io.*;

public class Calculateur {
    public static void main(String[] args) {
        File folder = new File("./fr/hetic/operations");

        if (!folder.exists()) {
            System.out.println("Directory does not exist");
            return;
        }

        processDirectory(folder);
    }

    private static void processDirectory(File folder) {
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            return;
        }

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".op")) {
                processFile(file);
            } else if (file.isDirectory()) {
                processDirectory(file);
            }
        }
    }

    private static void processFile(File file) {
        System.out.println("Processing file: " + file.getName());
        File resultFile = new File(file.getParent(), file.getName().replaceFirst("[.][^.]+$", "") + ".res");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             PrintWriter writer = new PrintWriter(new FileWriter(resultFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String result = processLine(line);
                    writer.println(result);
                } catch (OperationFormatException e) {
                    writer.println("ERROR");
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + file.getName());
            e.printStackTrace();
        }
    }

    private static String processLine(String line) throws OperationFormatException {
        String[] parts = line.split(";");
        if (parts.length != 3) {
            throw new OperationFormatException("Incorrect format");
        }

        try {
            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[1]);
            String op = parts[2].trim();

            switch (op) {
                case "+":
                    return String.format("%.2f", num1 + num2);
                case "-":
                    return String.format("%.2f", num1 - num2);
                case "*":
                    return String.format("%.2f", num1 * num2);
                default:
                    throw new OperationFormatException("Unknown operation");
            }
        } catch (NumberFormatException e) {
            throw new OperationFormatException("Invalid number format");
        }
    }

    static class OperationFormatException extends Exception {
        public OperationFormatException(String message) {
            super(message);
        }
    }
}
