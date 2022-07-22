package alert;

import battery.BatteryCharacter;
import enums.AlertTarget;
import enums.CoolingType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AlertTest
{
    private final ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
    private final PrintStream originalOutPut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(actualOutput));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOutPut);
    }

    @Test
    public void checkAlertForNormalTempMediumActiveCooling()
    {
        AlertCheck check = new AlertCheck();
        check.checkAndAlert(AlertTarget.TO_CONTROLLER,new BatteryCharacter(CoolingType.MED_ACTIVE_COOLING),0);
        assertEquals("feed : normal\n", actualOutput.toString());
    }

    @Test
    public void checkAlertForHighTempMediumActiveCooling()
    {
        AlertCheck check = new AlertCheck();
        check.checkAndAlert(AlertTarget.TO_CONTROLLER,new BatteryCharacter(CoolingType.MED_ACTIVE_COOLING),45);
        assertEquals("feed : high\n", actualOutput.toString());

    }

    @Test
    public void checkAlertForLowTempPassiveCooling()
    {
        AlertCheck check = new AlertCheck();
        check.checkAndAlert(AlertTarget.TO_CONTROLLER,new BatteryCharacter(CoolingType.PASSIVE_COOLING),-1);
        assertEquals("feed : low\n", actualOutput.toString());
    }

    @Test
    public void checkAlertForNormalTempPassiveCooling()
    {
        AlertCheck check = new AlertCheck();
        check.checkAndAlert(AlertTarget.TO_CONTROLLER,new BatteryCharacter(CoolingType.PASSIVE_COOLING),15);
        assertEquals("feed : normal\n", actualOutput.toString());
    }

    @Test
    public void checkAlertForLowTempHighActiveCooling()
    {
        AlertCheck check = new AlertCheck();
        String expectedResponse ="To: a.b@c.com\n" + "Hi, the temperature is too low";
        check.checkAndAlert(AlertTarget.TO_EMAIL,new BatteryCharacter(CoolingType.HI_ACTIVE_COOLING),-25);
        assertEquals(expectedResponse, actualOutput.toString().trim());

    }

    @Test
    public void checkAlertForNormalTempHighActiveCooling()
    {
        AlertCheck check = new AlertCheck();
        check.checkAndAlert(AlertTarget.TO_EMAIL,new BatteryCharacter(CoolingType.HI_ACTIVE_COOLING),42);
        assertEquals("", actualOutput.toString());
    }

    @Test
    public void checkAlertForHighTempHighActiveCooling()
    {
        AlertCheck check = new AlertCheck();
        String expectedResponse ="To: a.b@c.com\n" + "Hi, the temperature is too high";
        check.checkAndAlert(AlertTarget.TO_EMAIL,new BatteryCharacter(CoolingType.HI_ACTIVE_COOLING),49);
        assertEquals(expectedResponse, actualOutput.toString().trim());
    }

    @Test
    public void checkAlertForLowTempMediumActiveCooling()
    {
        AlertCheck check = new AlertCheck();
        String expectedResponse ="To: a.b@c.com\n" + "Hi, the temperature is too low";
        check.checkAndAlert(AlertTarget.TO_EMAIL,new BatteryCharacter(CoolingType.MED_ACTIVE_COOLING),-8);
        assertEquals(expectedResponse, actualOutput.toString().trim());
    }

    @Test
    public void checkAlertForHighTempPassiveCooling()
    {
        AlertCheck check = new AlertCheck();
        check.checkAndAlert(AlertTarget.TO_CONTROLLER,new BatteryCharacter(CoolingType.PASSIVE_COOLING),40);
        assertEquals("feed : high\n", actualOutput.toString());
    }
}
