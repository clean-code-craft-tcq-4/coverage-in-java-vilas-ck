package notification;

import enums.BreachType;

public class NotifyEmail implements SendNotification{
    @Override
    public void notify(BreachType breachType) {
        String recepient = "a.b@c.com";
        switch(breachType) {
            case TOO_LOW:
                send(BreachType.TOO_LOW.getBreach(), recepient);
                break;
            case TOO_HIGH:
                send(BreachType.TOO_HIGH.getBreach(), recepient);
                break;
            case NORMAL:
                break;
        }
    }
    private void send(String level, String recepient){
        System.out.printf("To: %s\n", recepient);
        System.out.println("Hi, the temperature is too "+level+"\n");
    }
}
