import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFileReader {
    private static final String OUTPUT_FILE_NAME = "resultatDatabase.txt";
    private static final String SELECT_FICHIERS = "SELECT * FROM Fichier WHERE TYPE = 'OP'";
    private static final String SELECT_LIGNES = "SELECT * FROM Ligne WHERE FICHIER_ID = ?";

    public void readFilesAndProcess() {
        List<Fichier> fichiers = getFichiers();

        File outputFile = new File(OUTPUT_FILE_NAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Fichier fichier : fichiers) {
                List<Ligne> lignes = getLignes(fichier.getId());
                for (Ligne ligne : lignes) {
                    double result = performOperation(ligne.getParam1(), ligne.getParam2(), ligne.getOperateur());
                    writer.write(String.format("Résultat de l'ID %d: %f%n", ligne.getId(), result));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double performOperation(int param1, int param2, String operateur) {
        switch (operateur) {
            case "+":
                return param1 + param2;
            case "-":
                return param1 - param2;
            case "*":
                return param1 * param2;
            default:
                throw new IllegalArgumentException("Opérateur inconnu: " + operateur);
        }
    }

    private List<Fichier> getFichiers() {
        List<Fichier> fichiers = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SELECT_FICHIERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                fichiers.add(new Fichier(
                    rs.getInt("ID"), 
                    rs.getString("NOM"), 
                    rs.getString("TYPE")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fichiers;
    }

    private List<Ligne> getLignes(int fichierId) {
        List<Ligne> lignes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SELECT_LIGNES)) {
            stmt.setInt(1, fichierId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lignes.add(new Ligne(
                        rs.getInt("ID"), 
                        rs.getInt("PARAM1"), 
                        rs.getInt("PARAM2"), 
                        rs.getString("OPERATEUR"), 
                        rs.getInt("INDEX"), 
                        rs.getInt("FICHIER_ID")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lignes;
    }
}

