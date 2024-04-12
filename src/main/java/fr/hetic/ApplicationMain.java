package fr.hetic;

public class ApplicationMain {
    public static void main(String[] args) {
        String implementationType = ConfigLoader.getProperty("implementation");

        if ("JDBC".equalsIgnoreCase(implementationType)) {
            DatabaseFileReader dbFileReader = new DatabaseFileReader();
            dbFileReader.readFilesAndProcess();
            System.out.println("Using DatabaseFileReader implementation.");
        } else if ("FILE".equalsIgnoreCase(implementationType)) {
            FileProcessor fileSystemReader = new FileProcessor();
            fileSystemReader.readFilesAndProcess();
            System.out.println("Using FileSystemFileReader implementation.");
        } else {
            System.out.println("No valid implementation specified.");
        }
    }
}
