package notification;

import enums.BreachType;

public class NotifyController implements SendNotification{
    @Override
    public void notify(BreachType breachType) {
        int header = 0xfeed;
        System.out.printf("%i : %i\n", header, breachType);
    }
}
