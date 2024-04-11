import java.util.Arrays;

public class FileProcessor {
    private File directory;

    public FileProcessor(String directoryPath) {
        this.directory = new File(directoryPath);
    }

    public void processFiles() {
        Arrays.stream(directory.listFiles((dir, name) -> name.endsWith(".op")))
              .forEach(this::processFile);
    }

    private void processFile(File file) {
        File resultFile = new File(file.getParent(), file.getName().replace(".op", ".res"));
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {

            reader.lines().forEach(line -> processLine(line, writer));
        } catch (IOException e) {
            System.err.println("Erreur de lecture/écriture : " + e.getMessage());
        }
    }

    private void processLine(String line, BufferedWriter writer) {
        String[] parts = line.split(";");
        if (parts.length != 3) {
            writeToFile(writer, "ERROR");
            return;
        }

        try {
            double op1 = Double.parseDouble(parts[0]);
            double op2 = Double.parseDouble(parts[1]);
            OperationStrategy strategy = OperationFactory.getOperationStrategy(parts[2]);
            double result = strategy.execute(op1, op2);
            writeToFile(writer, String.valueOf(result));
        } catch (Exception e) {
            writeToFile(writer, "ERROR");
        }
    }

    private void writeToFile(BufferedWriter writer, String content) {
        try {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file", e);
        }
    }
}
