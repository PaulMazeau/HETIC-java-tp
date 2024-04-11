import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionStrategyTest {

    @Test
    public void testAddition() {
        AdditionStrategy strategy = new AdditionStrategy();
        double result = strategy.execute(10, 15);
        assertEquals(25, result, "10 + 15 should equal 25");
    }
}
