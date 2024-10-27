package csit.semit.nyr.webappsnyrlab21.enums;

public enum DeliveryStatus {
    SENT("Sent"),
    RECEIVED("Received"),
    RETURNED("Returned");

    private final String displayName;

    DeliveryStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
