package notification;

import enums.BreachType;

public class NotifyController implements SendNotification{
    @Override
    public void notify(BreachType breachType) {
        int header = 0xfeed;
        System.out.printf("%s : %s\n", Integer.toHexString(header), breachType.getBreach());
    }
}
