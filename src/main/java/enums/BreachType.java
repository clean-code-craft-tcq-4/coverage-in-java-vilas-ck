package enums;

public enum BreachType {
    NORMAL("normal"),
    TOO_LOW("low"),
    TOO_HIGH("high");

    private String breach;

    BreachType(String breach) {
        this.breach = breach;
    }

    public String getBreach() {
        return breach;
    }
}