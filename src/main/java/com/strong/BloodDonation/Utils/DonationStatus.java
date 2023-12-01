package com.strong.BloodDonation.Utils;

public enum DonationStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");

    private final String displayValue;

    DonationStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
