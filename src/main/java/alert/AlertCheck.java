package alert;

import battery.BatteryCharacter;
import enums.AlertTarget;
import enums.BreachType;
import notification.NotifyController;
import notification.NotifyEmail;
import notification.SendNotification;

public class AlertCheck {

    SendNotification sendNotification;

    public void checkAndAlert(AlertTarget alertTarget, BatteryCharacter batteryChar, double temperatureInC) {

        BreachType breachType = inferBreach(temperatureInC, batteryChar.coolingType.getLowerLimit(), batteryChar.coolingType.getUpperLimit() );

        if(alertTarget.equals(AlertTarget.TO_CONTROLLER)) {
            sendNotification = new NotifyController();
        }else{
            sendNotification = new NotifyEmail();
        }
        sendNotification.notify(breachType);
    }

    public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
        if(value < lowerLimit) {
            return BreachType.TOO_LOW;
        }
        else if(value > upperLimit) {
            return BreachType.TOO_HIGH;
        }
        return BreachType.NORMAL;
    }
}
