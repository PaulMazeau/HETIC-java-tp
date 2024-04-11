import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionStrategyTest {

    @Test
    public void testSubtraction() {
        SubtractionStrategy strategy = new SubtractionStrategy();
        double result = strategy.execute(20, 10);
        assertEquals(10, result, "20 - 10 should equal 10");
    }
}
