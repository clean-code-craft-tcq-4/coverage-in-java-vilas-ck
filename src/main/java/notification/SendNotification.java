package notification;

import enums.BreachType;

public interface SendNotification {
    void notify(BreachType breachType);
}
