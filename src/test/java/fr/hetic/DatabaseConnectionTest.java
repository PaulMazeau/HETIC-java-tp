import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    public void testConnection() {
        assertNotNull(DatabaseConnection.connect(), "La connexion à la base de données a échoué.");
    }
}
