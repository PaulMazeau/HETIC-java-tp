import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationStrategyTest {

    @Test
    public void testMultiplication() {
        MultiplicationStrategy strategy = new MultiplicationStrategy();
        double result = strategy.execute(5, 4);
        assertEquals(20, result, "5 * 4 should equal 20");
    }
}
