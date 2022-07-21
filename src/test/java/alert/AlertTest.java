package alert;

import static org.junit.Assert.assertTrue;

import enums.BreachType;
import org.junit.Test;

public class AlertTest
{
    @Test
    public void infersBreachAsPerLimits()
    {
      assertTrue(AlertCheck.inferBreach(12, 20, 30) == BreachType.TOO_LOW);
      assertTrue(AlertCheck.inferBreach(55, 20, 45) == BreachType.TOO_HIGH);
      assertTrue(AlertCheck.inferBreach(40, 20, 40) == BreachType.NORMAL);
    }
}
