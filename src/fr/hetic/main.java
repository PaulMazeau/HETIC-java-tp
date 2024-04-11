public class CalculateurFichier {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CalculateurFichier <chemin_dossier>");
            return;
        }
        FileProcessor processor = new FileProcessor(args[0]);
        processor.processFiles();
    }
}
